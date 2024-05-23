package com.hellolaw.auth.service;

import reactor.core.publisher.Mono;

public interface AuthService {

	Mono<Void> saveRefreshToken(String accessToken, String refreshToken);

	Mono<Boolean> validateRefreshTokenInRedis(String refreshToken);
}