package com.hellolaw.hellolaw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.hellolaw.hellolaw.dto.LawDetailResponse;
import com.hellolaw.hellolaw.dto.LawRankingResponse;
import com.hellolaw.hellolaw.entity.Category;
import com.hellolaw.hellolaw.entity.Law;
import com.hellolaw.hellolaw.exception.HelloLawBaseException;
import com.hellolaw.hellolaw.mapper.LawMapper;
import com.hellolaw.hellolaw.repository.LawRepository;
import com.hellolaw.hellolaw.util.CategoryConverter;
import com.hellolaw.hellolaw.util.ErrorBase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LawServiceImpl implements LawService {

	private final LawRepository lawRepository;
	private final RedisTemplate<String, Object> redisTemplate;
	private final LawMapper lawMapper = LawMapper.INSTANCE;

	@Override
	public LawDetailResponse getLawDetail(String lawName) {
		Law law = lawRepository.findByName(lawName)
			.orElseThrow(() -> new HelloLawBaseException(ErrorBase.E400_INVALID_LAW_NAME));
		return lawMapper.toLawDetailResponse(law);
	}

	@Override
	public List<LawRankingResponse> getLawRanking(String category) {
		Category categoryKey = CategoryConverter.getCategoryInEnum(category);
		log.info("category: {}", categoryKey.toString().trim());
		Object lawsObj = redisTemplate.opsForList().leftPop(categoryKey.toString().trim());
		List<LawRankingResponse> lawsList = (List<LawRankingResponse>)lawsObj;
		List<LawRankingResponse> lawRankingResponseList = new ArrayList<>();

		if (lawsList == null || lawsList.isEmpty()) {
			log.info("lawList is null or empty");
			List<Law> lawRanking = lawRepository.findTop10ByCategoryOrderByCountDesc(categoryKey);
			int length = lawRanking.size();
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					LawRankingResponse lawRankingResponse = lawMapper.toLawRankingResponse(lawRanking.get(i));
					lawRankingResponse.setRank(i + 1);
					lawRankingResponseList.add(lawRankingResponse);
				}
			}
		} else {
			log.info("lawList: {}", lawsList);
			for (LawRankingResponse resonse : lawsList) {
				lawRankingResponseList.add(resonse);
			}
		}

		return lawRankingResponseList;
	}
}