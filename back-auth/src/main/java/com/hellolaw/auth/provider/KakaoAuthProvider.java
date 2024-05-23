package com.hellolaw.auth.provider;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.hellolaw.auth.client.kakao.KakaoAuthApiClient;
import com.hellolaw.auth.dto.KakaoUserInfoResponse;
import com.hellolaw.auth.dto.UserInfoResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class KakaoAuthProvider implements AuthProvider {
	private final KakaoAuthApiClient kakaoAuthApiClient;

	@Override
	public String getAccessToken(MultiValueMap<String, String> formData) {
		return kakaoAuthApiClient.getAccessToken(formData);
	}

	@Override
	public String getSocialId(String token) {
		return kakaoAuthApiClient.getProfileInfo("Bearer " + token).getId();
	}

	@Override
	public KakaoUserInfoResponse getUserInfo(String token) {
		return kakaoAuthApiClient.getUserInfo("Bearer " + token);
	}

	@Override
	public void logout(String id) {
		kakaoAuthApiClient.logoutKakao(id);
	}

	@Override
	public String getUserNickname(UserInfoResponse userInfoResponse) {
		KakaoUserInfoResponse kakaoUserInfoResponse = (KakaoUserInfoResponse)userInfoResponse;
		return kakaoUserInfoResponse.getKakao_account().getProfile().getNickname();
	}
}