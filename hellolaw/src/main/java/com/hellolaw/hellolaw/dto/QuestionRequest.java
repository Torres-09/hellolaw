package com.hellolaw.hellolaw.dto;

import lombok.Data;

@Data
public class QuestionRequest {
	private String category;
	private Boolean victim;
	private String question;
}
