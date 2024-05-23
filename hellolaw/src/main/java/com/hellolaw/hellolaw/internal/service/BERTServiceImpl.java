package com.hellolaw.hellolaw.internal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellolaw.hellolaw.internal.dto.PrecedentDto;
import com.hellolaw.hellolaw.internal.dto.SuggestionDto;

import reactor.core.publisher.Mono;

@Service
public class BERTServiceImpl implements BERTService {

	@Value("${hellolaw.ai.url}")
	private String AIUrl;

	public PrecedentDto getSimilarPrecedent(String text) throws JsonProcessingException {

		SuggestionDto request = new SuggestionDto(text);
		Mono<String> response = WebClient.create(AIUrl).post()
			.uri("/search/")
			.header("Content-Type", "application/json")
			.body(Mono.just(request), SuggestionDto.class)
			.retrieve()
			.bodyToMono(String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		PrecedentDto responseData = objectMapper.readValue(response.block(), PrecedentDto.class);
		responseData.setIndex(responseData.getIndex() + 1);
		return responseData;
	}

	@Override
	public SuggestionDto getSuggestion(String text) throws JsonProcessingException {
		SuggestionDto request = new SuggestionDto(text);
		Mono<String> response = WebClient.create(AIUrl).post()
			.uri("/answer/")
			.header("Content-Type", "application/json")
			.body(Mono.just(request), SuggestionDto.class)
			.retrieve()
			.bodyToMono(String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		SuggestionDto responseData = objectMapper.readValue(response.block(), SuggestionDto.class);
		return responseData;
	}
}
