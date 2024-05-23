package com.hellolaw.hellolaw.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hellolaw.hellolaw.dto.LawRankingResponse;
import com.hellolaw.hellolaw.entity.Category;
import com.hellolaw.hellolaw.entity.Law;
import com.hellolaw.hellolaw.mapper.LawMapper;
import com.hellolaw.hellolaw.repository.LawRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class LawRankingScheduler {
	private final LawRepository lawRepository;
	private final LawMapper lawMapper;
	private final RedisTemplate<String, Object> redisTemplate;

	//@Scheduled(cron = "0 0/1 * * * ?")    // 1분마다 실행
	@Scheduled(cron = "0 0 */1 * * ?")    // 정각마다 실행
	public void updateLawRanking() {
		log.info("-----Running Scheduled Task-----");
		Category[] categories = Category.values();
		for (Category category : categories) {
			log.info("category: {}", category);
			String categoryKey = category.name();
			List<Law> lawRanking = lawRepository.findTop10ByCategoryOrderByCountDesc(category);
			List<LawRankingResponse> lawList = new ArrayList<>();

			int length = lawRanking.size();
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					LawRankingResponse response = lawMapper.toLawRankingResponse(lawRanking.get(i));
					log.info("rank: {} response: {}", i + 1, response);
					response.setRank(i + 1);
					lawList.add(response);
				}

				Long size = redisTemplate.opsForList().size(categoryKey);
				if (size != null && size > 0) {
					redisTemplate.delete(categoryKey);
				}
			}
			redisTemplate.opsForList().leftPush(categoryKey, lawList);
		}

	}

}
