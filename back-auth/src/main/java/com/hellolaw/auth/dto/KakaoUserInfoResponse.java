package com.hellolaw.auth.dto;

import com.hellolaw.auth.client.kakao.KakaoAccount;

import lombok.Data;

@Data
public class KakaoUserInfoResponse extends UserInfoResponse {
	private KakaoAccount kakao_account;
}