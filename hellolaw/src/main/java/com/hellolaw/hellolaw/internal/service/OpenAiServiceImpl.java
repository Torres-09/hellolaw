package com.hellolaw.hellolaw.internal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellolaw.hellolaw.exception.HelloLawBaseException;
import com.hellolaw.hellolaw.internal.dto.OpenAiRequestDto;
import com.hellolaw.hellolaw.internal.dto.OpenAiResponseDto;
import com.hellolaw.hellolaw.internal.dto.PrecedentSummaryResponse;
import com.hellolaw.hellolaw.util.ErrorBase;
import com.hellolaw.hellolaw.util.PromptConstant;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenAiServiceImpl implements OpenAiService {

	private final WebClient webClient;

	@Value("${openai.api.model}")
	private String model;

	@Value("${openai.api.url}")
	private String openAiUrl;

	public PrecedentSummaryResponse getBasicFactInformation(String disposal, String basicFact) {
		// TODO : 분리
		String prompt = PromptConstant.Precedent.prefix + disposal + "\n" + basicFact + PromptConstant.Precedent.suffix;
		OpenAiRequestDto request = new OpenAiRequestDto(model, prompt);
		OpenAiResponseDto response = webClient.post()
			.uri(openAiUrl)
			.body(BodyInserters.fromValue(request))
			.retrieve()
			.bodyToMono(OpenAiResponseDto.class)
			.block();
		if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
			throw new HelloLawBaseException(ErrorBase.E500_INTERNAL_SERVER);
		}
		String json = response.getChoices().get(0).getMessage().getContent();
		PrecedentSummaryResponse precedentSummaryResponse;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			precedentSummaryResponse = objectMapper.readValue(json,
				PrecedentSummaryResponse.class);
			System.out.println(precedentSummaryResponse.toString());
		} catch (Exception e) {
			throw new HelloLawBaseException(ErrorBase.E500_INTERNAL_SERVER);
		}
		return precedentSummaryResponse;
	}
}
