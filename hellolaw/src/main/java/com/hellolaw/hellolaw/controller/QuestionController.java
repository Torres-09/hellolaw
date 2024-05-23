package com.hellolaw.hellolaw.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hellolaw.hellolaw.annotation.UserId;
import com.hellolaw.hellolaw.common.ApiResponse;
import com.hellolaw.hellolaw.dto.QuestionAnswerResponse;
import com.hellolaw.hellolaw.dto.QuestionHistoryResponse;
import com.hellolaw.hellolaw.dto.QuestionRequest;
import com.hellolaw.hellolaw.internal.service.BERTService;
import com.hellolaw.hellolaw.service.QuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

	private final BERTService bertService;
	private final QuestionService questionService;

	@GetMapping("/history")
	public ResponseEntity<ApiResponse<List<QuestionHistoryResponse>>> getTwoQuestionHistoryList(@UserId Long userId) {
		log.info("-----------getTwoQuestionsList");
		List<QuestionHistoryResponse> list = questionService.getTwoQuestionHistoryList(userId);
		return ResponseEntity.ok(ApiResponse.success(list));
	}

	@GetMapping("/v1")
	public void questionHelloV1() throws JsonProcessingException {
		log.info(bertService.getSimilarPrecedent("test").toString());
		return;
	}

	@PostMapping
	public ResponseEntity<QuestionAnswerResponse> generateAnswer(@UserId Long userId,
		@RequestBody QuestionRequest questionRequest) throws
		JsonProcessingException {
		log.info("-----------generateAnswer" + userId);
		return ResponseEntity.ok(questionService.generateAnswer(userId, questionRequest));
	}

	@DeleteMapping("/v1")
	public ResponseEntity<ApiResponse<Void>> deleteQuestion(
		@UserId Long userId,
		@RequestParam(value = "questionId") Long questionId) {
		return ResponseEntity.ok(ApiResponse.success(questionService.deleteQuestion(userId, questionId)));
	}
}
