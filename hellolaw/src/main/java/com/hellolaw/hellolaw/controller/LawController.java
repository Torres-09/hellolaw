package com.hellolaw.hellolaw.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hellolaw.hellolaw.common.ApiResponse;
import com.hellolaw.hellolaw.service.LawService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/law")
@RequiredArgsConstructor
public class LawController {

	private final LawService lawService;

	@GetMapping("/detail")
	public ResponseEntity<?> getLawDetail(@RequestParam(value = "name") String lawName) {
		return ResponseEntity.ok(lawService.getLawDetail(lawName));
	}

	@GetMapping("/ranking")
	public ResponseEntity<ApiResponse<List<?>>> getLawRanking(
		@RequestParam(value = "category") String category) {
		return ResponseEntity.ok(ApiResponse.success(lawService.getLawRanking(category)));
	}
}
