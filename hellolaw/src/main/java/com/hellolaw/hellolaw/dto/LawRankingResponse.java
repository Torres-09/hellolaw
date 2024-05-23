package com.hellolaw.hellolaw.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
public class LawRankingResponse implements Serializable {

	private Long lawId;
	private String lawName;
	private int rank;

	public LawRankingResponse() {
	}

	@Builder
	public LawRankingResponse(Long lawId, String lawName, int rank) {
		this.lawId = lawId;
		this.lawName = lawName;
		this.rank = rank;
	}
}
