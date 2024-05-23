package com.hellolaw.auth.filter;

import java.time.Duration;

import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.hellolaw.auth.service.AuthService;
import com.hellolaw.auth.util.JWTProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class JWTAuthenticationFilter implements WebFilter {
	private final JWTProvider jwtProvider;
	private final AuthService authService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		log.info("JWT 필터 실행");
		log.info(exchange.getRequest().getMethod().toString());
		return getAccessToken(exchange.getRequest().getCookies())
			.flatMap(accessToken -> {
				if (jwtProvider.isValidateToken(accessToken)) {
					return updateSecurityContext(accessToken)
						.flatMap(securityContext -> chain.filter(exchange)
							.contextWrite(
								ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext))));
				} else {
					return validateRefreshTokenAndReIssueAccessToken(exchange, accessToken, chain);
				}
			})
			.switchIfEmpty(chain.filter(exchange)) // 쿠키가 없는 경우 체인을 계속 진행
			.onErrorResume(e -> {
				log.error("Error during JWT filter processing", e);
				return chain.filter(exchange);
			});
	}

	private Mono<String> getAccessToken(MultiValueMap<String, HttpCookie> cookies) {
		log.info("쿠키에서 액세스 토큰 추출 시도");
		HttpCookie accessTokenCookie = cookies.getFirst("access-token");
		if (accessTokenCookie == null) {
			log.warn("액세스 토큰 쿠키가 존재하지 않습니다.");
			return Mono.empty();
		}
		String accessToken = accessTokenCookie.getValue();
		log.info("액세스 토큰 추출 성공: {}", accessToken);
		return Mono.just(accessToken);
	}

	private Mono<SecurityContext> updateSecurityContext(String accessToken) {
		return Mono.defer(() -> {
			Authentication authentication = jwtProvider.getAuthentication(accessToken);
			SecurityContextImpl securityContext = new SecurityContextImpl(authentication);
			SecurityContextHolder.setContext(securityContext);
			log.info("인증 정보로 SecurityContext 업데이트: {}", securityContext.getAuthentication().isAuthenticated());
			return Mono.just(securityContext);
		});
	}

	private Mono<String> reIssueAccessToken(String accessToken) {
		log.info("액세스 토큰 재발급");
		return Mono.just(jwtProvider.createAccessToken(jwtProvider.getId(accessToken),
			jwtProvider.getProvider(accessToken)));
	}

	private void addAccessTokenCookie(ServerWebExchange exchange, String accessToken) {
		log.info("액세스 토큰 쿠키에 더하기");
		ResponseCookie cookie = ResponseCookie.from("access-token", accessToken)
			.httpOnly(false)
			.maxAge(Duration.ofDays(30))
			.path("/")
			.build();
		exchange.getResponse().addCookie(cookie);
	}

	private Mono<Void> validateRefreshTokenAndReIssueAccessToken(ServerWebExchange exchange, String accessToken,
		WebFilterChain chain) {
		log.info("레디스 이용해서 재발급");
		return authService.validateRefreshTokenInRedis(accessToken)
			.flatMap(isValidRefreshToken -> {
				if (isValidRefreshToken) {
					return reIssueAccessToken(accessToken)
						.flatMap(newAccessToken -> {
							addAccessTokenCookie(exchange, newAccessToken);
							return updateSecurityContext(newAccessToken)
								.flatMap(securityContext -> chain.filter(exchange)
									.contextWrite(
										ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext))));
						});
				} else {
					SecurityContextHolder.clearContext();
					return chain.filter(exchange);
				}
			});
	}
}