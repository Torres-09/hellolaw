package com.hellolaw.auth.config;

import static org.springframework.security.config.Customizer.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.hellolaw.auth.filter.JWTAuthenticationFilter;
import com.hellolaw.auth.handler.CustomOAuth2SuccessHandler;
import com.hellolaw.auth.service.AuthService;
import com.hellolaw.auth.util.JWTProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

	private final JWTProvider jwtProvider;
	private final AuthService authService;
	private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
	private final JWTAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
		http
			.cors(withDefaults())
			.authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
				.pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.pathMatchers("/login/**").permitAll()
				.pathMatchers("/health").permitAll()
				.anyExchange().authenticated())
			.oauth2Login(oAuth2LoginSpec -> oAuth2LoginSpec
				.authenticationSuccessHandler(customOAuth2SuccessHandler)
			)
			.oauth2Client(withDefaults())
			.addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
		;

		return http.build();
	}

	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);

		List<String> origins = Arrays.asList(
			"http://localhost:3000",
			"https://k10a506.p.ssafy.io",
			"https://accounts.kakao.com",
			"https://test.hellolaw.kr",
			"https://hellolaw.kr"
		);
		config.setAllowedOrigins(origins);
		config.addExposedHeader("Authorization");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	MapReactiveUserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();
		return new MapReactiveUserDetailsService(userDetails);
	}
}