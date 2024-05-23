package com.hellolaw.hellolaw.exception;

import com.hellolaw.hellolaw.util.ErrorBase;

import lombok.Getter;

@Getter
public class HelloLawBaseException extends RuntimeException {

	private final ErrorBase errorBase;

	public HelloLawBaseException(ErrorBase errorBase) {
		super(errorBase.getMessage());
		this.errorBase = errorBase;
	}
}