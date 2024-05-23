package com.hellolaw.auth.service;

import org.springframework.stereotype.Service;

import com.hellolaw.auth.redis.RedisService;
import com.hellolaw.auth.util.JWTProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
	private final RedisService redisService;
	private final JWTProvider jwtProvider;

	@Override
	public Mono<Void> saveRefreshToken(String accessToken, String refreshToken) {
		Long principal = jwtProvider.getClaims(accessToken).get("id", Long.class);
		return redisService.setValuesWithTimeout("RT:" + principal, refreshToken,
				jwtProvider.getTokenExpirationTime(refreshToken))
			.then();
	}

	@Override
	public Mono<Boolean> validateRefreshTokenInRedis(String accessToken) {
		return Mono.justOrEmpty(jwtProvider.getId(accessToken))
			.flatMap(id -> {
				String key = "RT:" + id;
				return redisService.getValues(key)
					.flatMap(refreshTokenInRedis -> {
						if (refreshTokenInRedis == null) {
							return Mono.just(false);
						}
						return Mono.just(jwtProvider.isValidateToken(refreshTokenInRedis));
					})
					.switchIfEmpty(Mono.just(false));
			});
	}
}