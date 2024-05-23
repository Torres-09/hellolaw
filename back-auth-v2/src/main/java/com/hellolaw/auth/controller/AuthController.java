package com.hellolaw.auth.controller;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.hellolaw.auth.dto.api.ApiResponse;
import com.hellolaw.auth.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	@Value("${auth.controller.redirect-url}")
	private String OAuthRedirectURL;

	@GetMapping("/logout")
	Mono<ResponseEntity<ApiResponse<Void>>> logout(Authentication authentication, ServerWebExchange exchange) {

		ResponseCookie cookie = ResponseCookie.from("access-token", null)
			.path("/")
			.maxAge(Duration.ofMillis(0))
			.httpOnly(true)
			.build();

		exchange.getResponse().addCookie(cookie);
		exchange.getResponse().setStatusCode(HttpStatus.SEE_OTHER);
		exchange.getResponse().getHeaders().setLocation(URI.create(OAuthRedirectURL));

		Long userId = (Long)authentication.getPrincipal();
		userService.logout(userId);
		SecurityContextHolder.clearContext();

		return Mono.just(ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success()));
	}
}