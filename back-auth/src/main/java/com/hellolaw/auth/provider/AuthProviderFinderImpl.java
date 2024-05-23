package com.hellolaw.auth.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthProviderFinderImpl implements AuthProviderFinder {
	private static final Map<String, AuthProvider> authProviderMap = new HashMap<>();

	// private final GoogleAuthProvider googleAuthProvider;
	private final KakaoAuthProvider kakaoAuthProvider;

	@PostConstruct
	void initializeAuthProviders() {
		// authProviderMap.put("구글", googleAuthProvider);
		authProviderMap.put("카카오", kakaoAuthProvider);
	}

	@Override
	public AuthProvider findAuthProvider(String socialType) {
		AuthProvider authProvider = authProviderMap.get(socialType);

		// 지원하지 않는 소셜타입에 대한 예외처리 적용 -> 세부 에러로 나중에 조정 필요
		if (authProvider == null) {
			throw new IllegalArgumentException("지원하지 않는 소셜로그인입니다.");
		}
		return authProvider;
	}
}