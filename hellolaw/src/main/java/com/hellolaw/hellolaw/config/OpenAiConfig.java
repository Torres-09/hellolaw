package com.hellolaw.hellolaw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenAiConfig {

	@Value("${openai.api.secret-key}")
	private String secretKey;

	private final String AuthorizationHeader = "Authorization";
	private final String tokenPrefix = "Bearer ";

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
			.filter((request, next) -> {
				ClientRequest filteredRequest = ClientRequest.from(request)
					.header(AuthorizationHeader, tokenPrefix + secretKey)
					.build();
				return next.exchange(filteredRequest);
			})
			.build();
	}
}
