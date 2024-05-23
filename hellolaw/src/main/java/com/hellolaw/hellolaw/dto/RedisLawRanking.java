package com.hellolaw.hellolaw.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class RedisLawRanking {
	private List<LawRankingResponse> redisLawRanking = new ArrayList<>();

	public RedisLawRanking(List<LawRankingResponse> redisLawRanking) {
		this.redisLawRanking = redisLawRanking;
	}
}
