package com.hellolaw.hellolaw.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestMessage {

	private String role = "user";
	private String content;

	public RequestMessage(String content) {
		this.content = content;
	}
}
