package com.hellolaw.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class HealthcheckController {

	@GetMapping("/health")
	public Mono<String> check() {
		return Mono.just("health");
	}

}
