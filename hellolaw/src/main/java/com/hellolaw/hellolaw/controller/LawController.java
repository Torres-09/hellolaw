package com.hellolaw.hellolaw.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hellolaw.hellolaw.common.ApiResponse;
import com.hellolaw.hellolaw.dto.LawRankingResponse;
import com.hellolaw.hellolaw.entity.Category;
import com.hellolaw.hellolaw.service.LawService;
import com.hellolaw.hellolaw.util.CategoryConverter;

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
	public ResponseEntity<ApiResponse<List<LawRankingResponse>>> getLawRanking(
		@RequestParam(value = "category") String param) {
		// memberId 임시 설정
		Long memberId = 100L;
		Category category = CategoryConverter.getCategoryInEnum(param);
		return ResponseEntity.ok(ApiResponse.success(lawService.getLawRanking(memberId, category)));
	}
}
