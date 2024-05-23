package com.hellolaw.hellolaw.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LawInformationDto {
	private String lawName;
	private String contents;
	private String category;
}
