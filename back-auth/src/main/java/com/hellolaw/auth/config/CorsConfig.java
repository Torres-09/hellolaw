package com.hellolaw.auth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CorsConfig {

	// 가장 먼저 적용
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public CorsFilter corsFilter() {

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		// 자격 증명 허용
		config.setAllowCredentials(true);

		// 개발 중에는 모든 오리진 허용
		List<String> origin = Arrays.asList("http://localhost:3000", "https://k10a506.p.ssafy.io",
			"https://accounts.kakao.com",
			"https://test.hellolaw.kr",
			"https://hellolaw.kr");
		config.setAllowedOrigins(origin);

		config.addExposedHeader("Authorization"); // 클라이언트에서 이거 읽을 수 있음
		config.addAllowedHeader("*"); // 모든 헤더 가능
		config.addAllowedMethod("*"); // 모든 메서드 허용

		source.registerCorsConfiguration("/**", config); // 모든 경로에서 config 적용

		return new CorsFilter(source);
	}
}