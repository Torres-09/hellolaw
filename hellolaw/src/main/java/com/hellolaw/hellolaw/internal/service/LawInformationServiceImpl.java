package com.hellolaw.hellolaw.internal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.hellolaw.hellolaw.internal.dto.LawInformationDto;

@Service
public class LawInformationServiceImpl implements LawInformationService {

	@Value("${hellolaw.crawling.url}")
	private String CrawlingUrl;

	public LawInformationDto getLawInformation(String lawName) {
		LawInformationDto response = RestClient.create(CrawlingUrl).get()
			.uri("/law?name=" + lawName)
			.retrieve()
			.body(LawInformationDto.class);
		return response;
	}
}