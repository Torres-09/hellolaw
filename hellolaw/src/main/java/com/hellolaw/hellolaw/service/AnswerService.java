package com.hellolaw.hellolaw.service;

import com.hellolaw.hellolaw.dto.AnswerResultResponse;

public interface AnswerService {
	AnswerResultResponse getAnswerResult(Long userId, Long questionId);
}
