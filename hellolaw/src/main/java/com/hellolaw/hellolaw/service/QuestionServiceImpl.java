package com.hellolaw.hellolaw.service;

import static com.hellolaw.hellolaw.dto.QuestionHistoryResponse.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hellolaw.hellolaw.dto.QuestionAnswerResponse;
import com.hellolaw.hellolaw.dto.QuestionHistoryResponse;
import com.hellolaw.hellolaw.dto.QuestionRequest;
import com.hellolaw.hellolaw.entity.Answer;
import com.hellolaw.hellolaw.entity.Category;
import com.hellolaw.hellolaw.entity.Law;
import com.hellolaw.hellolaw.entity.Precedent;
import com.hellolaw.hellolaw.entity.Question;
import com.hellolaw.hellolaw.entity.RelatedAnswer;
import com.hellolaw.hellolaw.entity.User;
import com.hellolaw.hellolaw.exception.HelloLawBaseException;
import com.hellolaw.hellolaw.internal.dto.LawInformationDto;
import com.hellolaw.hellolaw.internal.dto.PrecedentDto;
import com.hellolaw.hellolaw.internal.dto.PrecedentSummaryResponse;
import com.hellolaw.hellolaw.internal.service.BERTService;
import com.hellolaw.hellolaw.internal.service.LawInformationService;
import com.hellolaw.hellolaw.internal.service.OpenAiService;
import com.hellolaw.hellolaw.mapper.AnswerMapper;
import com.hellolaw.hellolaw.mapper.QuestionMapper;
import com.hellolaw.hellolaw.mapper.SummaryAnswerMapper;
import com.hellolaw.hellolaw.repository.AnswerRepository;
import com.hellolaw.hellolaw.repository.LawRepository;
import com.hellolaw.hellolaw.repository.PrecedentRepository;
import com.hellolaw.hellolaw.repository.QuestionRepository;
import com.hellolaw.hellolaw.repository.RelatedAnswerRepository;
import com.hellolaw.hellolaw.repository.SummaryAnswerRepository;
import com.hellolaw.hellolaw.repository.UserRepository;
import com.hellolaw.hellolaw.util.CategoryConstant;
import com.hellolaw.hellolaw.util.CategoryConverter;
import com.hellolaw.hellolaw.util.ErrorBase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableAsync(proxyTargetClass = true)
public class QuestionServiceImpl implements QuestionService {

	private final QuestionRepository questionRepository;
	private final LawRepository lawRepository;
	private final AnswerRepository answerRepository;
	private final SummaryAnswerRepository summaryAnswerRepository;
	private final RelatedAnswerRepository relatedAnswerRepository;
	private final BERTService bertService;
	private final LawInformationService lawInformationService;
	private final OpenAiService openAiService;
	private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;
	private final UserRepository userRepository;
	private final AnswerMapper answerMapper;
	private final SummaryAnswerMapper summaryAnswerMapper;
	private final PrecedentRepository precedentRepository;
	
	@Override
	public List<QuestionHistoryResponse> getTwoQuestionHistoryList(Long userId) {

		List<Question> questions = questionRepository.findTop2ByUserIdOrderByCreatedAtDesc(userId);
		log.info("history-questions : " + questions.size());

		// TODO : 최근 2개의 질문 가져오기
		return questions.stream()
			.limit(2)
			.map(question -> Optional.ofNullable(createQuestionHistoryResponse(question)))
			.flatMap(Optional::stream) // 이렇게 하면 Optional.empty() 결과를 건너뜁니다
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public QuestionAnswerResponse generateAnswer(Long userId, QuestionRequest questionRequest)
		throws JsonProcessingException {
		Question question = saveQuestion(userId, questionRequest);
		Category questionCategory = questionRequest.getCategory() == null ? Category.OTHER :
			CategoryConverter.getCategoryInEnum(questionRequest.getCategory());
		String prompt = makePrompt(questionRequest);

		String suggestion = bertService.getSuggestion(prompt).getText(); // 대처 방안
		Answer answer = answerRepository.save(answerMapper.toAnswer(suggestion, question)); // 대처방안 저장

		PrecedentDto similarPrecedent = bertService.getSimilarPrecedent(prompt); // 유사판례
		Precedent precedent = precedentRepository.findById(similarPrecedent.getIndex())
			.orElseThrow(() -> new HelloLawBaseException(ErrorBase.E400_INVALID)); // 판례
		PrecedentSummaryResponse precedentSummary = openAiService.getBasicFactInformation(
			similarPrecedent.getDisposal_content(),
			similarPrecedent.getBasic_fact()); // 판례 요약
		summaryAnswerRepository.save(summaryAnswerMapper.toSummaryAnswer(precedentSummary.getSummary(), answer));

		List<String> list = similarPrecedent.getRelate_laword();
		list.forEach(lawName -> {
			Law law = saveRelatedLaw(lawName, questionCategory);
			relatedAnswerRepository.save(new RelatedAnswer(answer, precedent, law));
		});

		return QuestionAnswerResponse.builder()
			.suggestion(suggestion)
			.category(CategoryConstant.getCategoryInKorean(precedentSummary.getCategory()))
			.precedentId(similarPrecedent.getIndex())
			.precedentSummary(precedentSummary.getSummary())
			.lawType(similarPrecedent.getCase_nm())
			.relatedLaws(similarPrecedent.getRelate_laword())
			.build();
	}

	private Law saveRelatedLaw(String lawName, Category category) {
		Optional<Law> law = lawRepository.findByName(lawName);
		if (law.isEmpty()) {
			Law newLaw = new Law(lawName, null, category);
			CompletableFuture.runAsync(() -> updateLawInformation(newLaw, category));
			return lawRepository.save(newLaw);
		} else {
			Law newLaw = law.get();
			newLaw.setCount(newLaw.getCount() + 1);
			return lawRepository.save(newLaw);
		}
	}

	private Question saveQuestion(Long userId, QuestionRequest questionRequest) {
		User user = userRepository.findById(userId).orElseThrow(() ->
			new HelloLawBaseException(ErrorBase.E401_UNAUTHORIZED));
		return questionRepository.save(questionMapper.toQuestion(questionRequest, user));
	}

	private void updateLawInformation(Law law, Category category) {
		LawInformationDto lawInformationDto = lawInformationService.getLawInformation(law.getName());
		law.setContents(lawInformationDto.getContents());
		law.setCategory(
			category == null ? CategoryConverter.getCategoryInEnum(lawInformationDto.getCategory()) : category);
		lawRepository.save(law);
	}

	@Override
	@Transactional
	public Void deleteQuestion(Long userId, Long questionId) {
		Question question = questionRepository.findQuestionByQuestionIdUsingFetchJoin(questionId).orElseThrow();
		questionRepository.delete(question);
		return null;
	}

	private String makePrompt(QuestionRequest questionRequest) {
		String question = questionRequest.getQuestion();
		if (questionRequest.getVictim() != null) {
			question = "i am " + (questionRequest.getVictim() ? "피해자" : "가해자") + "\n" + question;
		}
		if (questionRequest.getCategory() != null) {
			question = "this is about " + questionRequest.getCategory() + "\n" + question;
		}
		return question;
	}
}
