package com.hellolaw.hellolaw.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hellolaw.hellolaw.common.ApiResponse;
import com.hellolaw.hellolaw.dto.PrecedentDetailResponse;
import com.hellolaw.hellolaw.service.PrecedentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/precedent")
public class PrecedentController {
	private final PrecedentService precedentService;

	@GetMapping("/entire")
	public ResponseEntity<ApiResponse<PrecedentDetailResponse>> getPrecedentDetail(
		@RequestParam(value = "precedentId") Long precedentId) {
		{
			return ResponseEntity.ok(ApiResponse.success(precedentService.getPrecedentDetail(precedentId)));
		}
	}
}
