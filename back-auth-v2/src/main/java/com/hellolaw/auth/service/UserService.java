package com.hellolaw.auth.service;

public interface UserService {

	// TokenResponse login(UserInfoResponse userInfoResponse, AuthProvider authProvider);

	void logout(Long id);
}