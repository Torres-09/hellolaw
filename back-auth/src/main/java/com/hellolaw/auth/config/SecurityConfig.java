package com.hellolaw.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

import com.hellolaw.auth.filter.JWTAuthenticationFilter;
import com.hellolaw.auth.service.AuthService;
import com.hellolaw.auth.util.JWTProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

	private final JWTProvider jwtProvider;
	private final CorsConfig corsConfig;
	private final AuthService authservice;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeHttpRequests(authorizeRequests -> authorizeRequests
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/health")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/oauth2/authorize/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/kakao-oauth/**")).permitAll()
				.anyRequest().authenticated()
			)
			.httpBasic(AbstractHttpConfigurer::disable
			)
			.csrf(AbstractHttpConfigurer::disable
			)
			.logout(AbstractHttpConfigurer::disable
			)
			.formLogin(AbstractHttpConfigurer::disable
			)
			.oauth2Login(oauth2Login -> oauth2Login
				.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
					.baseUri("/oauth2/authorize")
				)
				.redirectionEndpoint(redirectionEndpoint -> redirectionEndpoint
					.baseUri("/kakao-oauth/**")
				)
			)
			.sessionManagement(sessionManagement -> sessionManagement
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.addFilter(corsConfig.corsFilter())
			.addFilterBefore(new JWTAuthenticationFilter(authservice, jwtProvider),
				UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
}