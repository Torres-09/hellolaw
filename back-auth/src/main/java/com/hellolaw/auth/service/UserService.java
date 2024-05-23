package com.hellolaw.auth.service;

import com.hellolaw.auth.dto.TokenResponse;
import com.hellolaw.auth.dto.UserInfoResponse;
import com.hellolaw.auth.provider.AuthProvider;

public interface UserService {

	TokenResponse login(UserInfoResponse userInfoResponse, AuthProvider authProvider);

	void logout(Long id, String socialId, AuthProvider authProvider);
}