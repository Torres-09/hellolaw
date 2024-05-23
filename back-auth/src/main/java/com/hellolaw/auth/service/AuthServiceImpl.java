package com.hellolaw.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.hellolaw.auth.dto.UserInfoResponse;
import com.hellolaw.auth.provider.AuthProvider;
import com.hellolaw.auth.redis.RedisService;
import com.hellolaw.auth.util.JWTProvider;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {
	private final RedisService redisService;
	private final JWTProvider jwtProvider;
	//yml 파일에서 받아오기
	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	String redirectURI;
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	String clientSecret;
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	String clientId;

	@Override
	public String getAccessToken(String code, AuthProvider authProvider) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("code", code);
		formData.add("grant_type", "authorization_code");
		formData.add("redirect_uri", redirectURI);
		formData.add("client_id", clientId);
		// 필수는 아님
		formData.add("client_secret", clientSecret);
		log.info(formData.toString());

		return authProvider.getAccessToken(formData);
	}

	@Override
	public UserInfoResponse getUserInfo(String accessToken, AuthProvider authProvider) {
		return authProvider.getUserInfo(accessToken);
	}

	@Override
	public void saveRefreshToken(String accessToken, String refreshToken) {
		Long principal = jwtProvider.getClaims(accessToken).get("id", Long.class);
		redisService.setValuesWithTimeout("RT" + ":" + principal,
			refreshToken,
			jwtProvider.getTokenExpirationTime(refreshToken));
	}

	@Override
	public boolean validateRefreshTokenInRedis(String accessToken) {
		Long id = jwtProvider.getId(accessToken);
		String refreshTokenInRedis = redisService.getValues("RT" + ":" + id);
		log.info(refreshTokenInRedis);
		if (refreshTokenInRedis == null) {
			return false;
		}
		return jwtProvider.isValidateToken(refreshTokenInRedis);
	}

}