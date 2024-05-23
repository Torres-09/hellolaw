package com.hellolaw.hellolaw.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionAnswerResponse {
	private String suggestion;
	private Long precedentId;
	private String precedentSummary;
	private String lawType;
	private String category;
	private List<String> relatedLaws;
}
