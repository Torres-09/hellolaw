package com.hellolaw.auth.exception;

import com.hellolaw.auth.dto.ErrorBase;

import lombok.Getter;

@Getter
public abstract class HelloLawBaseException extends RuntimeException {

	private final ErrorBase errorBase;

	public HelloLawBaseException(ErrorBase errorBase) {
		super(errorBase.getMessage());
		this.errorBase = errorBase;
	}
}