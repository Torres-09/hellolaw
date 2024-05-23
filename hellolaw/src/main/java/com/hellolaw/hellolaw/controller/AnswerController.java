package com.hellolaw.hellolaw.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hellolaw.hellolaw.annotation.UserId;
import com.hellolaw.hellolaw.common.ApiResponse;
import com.hellolaw.hellolaw.dto.AnswerResultResponse;
import com.hellolaw.hellolaw.service.AnswerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
	private final AnswerService answerService;

	@GetMapping("/detail")
	public ResponseEntity<ApiResponse<AnswerResultResponse>> getQuestionResultDetail(
		@UserId Long userId,
		@RequestParam(value = "questionId") Long questionId
	) {
		return ResponseEntity.ok(ApiResponse.success(answerService.getAnswerResult(userId, questionId)));
	}
}
