package com.hellolaw.hellolaw.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HelloLawExceptionResponse {

	int errorCode;
	String errorMessage;
}
