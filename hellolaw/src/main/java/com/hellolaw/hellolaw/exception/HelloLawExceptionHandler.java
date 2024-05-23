package com.hellolaw.hellolaw.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HelloLawExceptionHandler {

	@ExceptionHandler(HelloLawBaseException.class)
	private ResponseEntity<HelloLawExceptionResponse> GlobalException(HelloLawBaseException helloLawBaseException) {
		return ResponseEntity.status(helloLawBaseException.getErrorBase().getStatusCode())
			.body(HelloLawExceptionResponse.builder()
				.errorCode(helloLawBaseException.getErrorBase().getStatusCode().value())
				.errorMessage(helloLawBaseException.getErrorBase().getMessage())
				.build());
	}
}
