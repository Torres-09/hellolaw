package com.hellolaw.auth.exception;

import com.hellolaw.auth.dto.ErrorBase;

public class InvalidException extends HelloLawBaseException {

	public InvalidException(ErrorBase errorBase) {
		super(errorBase);
	}
}