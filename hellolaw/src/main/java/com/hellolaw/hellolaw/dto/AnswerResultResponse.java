package com.hellolaw.hellolaw.dto;

import java.util.ArrayList;
import java.util.List;

import com.hellolaw.hellolaw.entity.Answer;
import com.hellolaw.hellolaw.entity.RelatedAnswer;
import com.hellolaw.hellolaw.util.CategoryConstant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerResultResponse {
	private Long precedentId;
	private String lawType;
	private String category;
	private String precedentSummary;
	private String suggestion;
	private List<String> relatedLaws;

	public static AnswerResultResponse createAnswerResultResponse(
		Answer answer
	) {
		List<String> lawNames = new ArrayList<>();

		for (RelatedAnswer relatedAnswer : answer.getRelatedAnswers()) {
			lawNames.add(relatedAnswer.getLaw().getName());
		}

		return AnswerResultResponse.builder()
			.precedentId(answer.getRelatedAnswers().get(0).getPrecedent().getId())
			.lawType(answer.getRelatedAnswers().get(0).getPrecedent().getCaseName())
			.category(
				CategoryConstant.getCategoryInKorean(answer.getRelatedAnswers().get(0).getLaw().getCategory().name()))
			.precedentSummary(answer.getSummaryAnswer().getContents())
			.suggestion(answer.getContents())
			.relatedLaws(lawNames)
			.build();
	}
}
