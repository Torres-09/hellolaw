package com.hellolaw.hellolaw.service;

import org.springframework.stereotype.Service;

import com.hellolaw.hellolaw.dto.AnswerResultResponse;
import com.hellolaw.hellolaw.entity.Answer;
import com.hellolaw.hellolaw.repository.AnswerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerServiceImpl implements AnswerService {
	private final AnswerRepository answerRepository;

	@Override
	public AnswerResultResponse getAnswerResult(Long userId, Long questionId) {
		Answer answer = answerRepository.findAnswerByQuestionIdUsingFetchJoin(questionId)
			.orElseThrow();

		return AnswerResultResponse.createAnswerResultResponse(answer);
	}
}
