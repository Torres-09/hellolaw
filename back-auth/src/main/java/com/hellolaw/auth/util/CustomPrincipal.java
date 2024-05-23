package com.hellolaw.auth.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomPrincipal {
	private Long id;
	private String socialId;
	private String provider;
}
