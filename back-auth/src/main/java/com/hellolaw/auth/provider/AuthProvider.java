package com.hellolaw.auth.provider;

import org.springframework.util.MultiValueMap;

import com.hellolaw.auth.dto.UserInfoResponse;

public interface AuthProvider {
	String getAccessToken(MultiValueMap<String, String> formData);

	String getSocialId(String token);

	UserInfoResponse getUserInfo(String accessToken);

	void logout(String id);

	String getUserNickname(UserInfoResponse userInfoResponse);
}