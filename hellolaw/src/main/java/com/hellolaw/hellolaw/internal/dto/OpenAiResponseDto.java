package com.hellolaw.hellolaw.internal.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiResponseDto {

	private List<Choice> choices;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Choice {

		private int index;
		private RequestMessage message;
	}
}
