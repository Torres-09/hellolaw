package com.hellolaw.auth.service;

import com.hellolaw.auth.dto.UserInfoResponse;
import com.hellolaw.auth.provider.AuthProvider;

public interface AuthService {

	String getAccessToken(String code, AuthProvider authProvider);

	UserInfoResponse getUserInfo(String accessToken, AuthProvider authProvider);

	void saveRefreshToken(String accessToken, String refreshToken);

	boolean validateRefreshTokenInRedis(String refreshToken);
}