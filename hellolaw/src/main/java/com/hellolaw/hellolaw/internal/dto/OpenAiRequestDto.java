package com.hellolaw.hellolaw.internal.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OpenAiRequestDto {

	private String model;
	private List<RequestMessage> messages;
	private final Double temperature = 1.0;

	public OpenAiRequestDto(String model, String prompt) {
		this.model = model;
		this.messages = new ArrayList<>();
		this.messages.add(new RequestMessage(prompt));
	}
}
