package com.hellolaw.auth.dto;

public class KakaoTokenResponse extends TokenResponse {
	public KakaoTokenResponse(String access_token, String refresh_token) {
		super(access_token, refresh_token);
	}

	private String token_type;
	private Integer expires_in;
	private Integer refresh_token_expires_in;
}