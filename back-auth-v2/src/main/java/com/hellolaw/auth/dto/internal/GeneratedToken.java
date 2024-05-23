package com.hellolaw.auth.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeneratedToken {

	private String grantType;

	private String accessToken;

	private String refreshToken;

}