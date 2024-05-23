package com.hellolaw.auth.dto;

import java.util.Map;

import com.hellolaw.auth.model.User;

import lombok.Builder;

@Builder
public record OAuth2UserInfo(
	String name,
	String email,
	String provider
) {

	public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equals("google")) {
			return ofGoogle(attributes);
		} else {
			throw new RuntimeException("cannot find registrationId");
		}
	}

	private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
		return OAuth2UserInfo.builder()
			.name((String)attributes.get("name"))
			.email((String)attributes.get("email"))
			.provider("google")
			.build();
	}

	public User toEntity() {
		return User.builder()
			.name(name)
			.email(email)
			.provider(provider)
			.role("USER")
			.build();
	}

}
