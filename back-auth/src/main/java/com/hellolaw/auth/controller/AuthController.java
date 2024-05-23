package com.hellolaw.auth.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hellolaw.auth.dto.ApiResponse;
import com.hellolaw.auth.dto.TokenResponse;
import com.hellolaw.auth.dto.UserInfoResponse;
import com.hellolaw.auth.provider.AuthProvider;
import com.hellolaw.auth.provider.AuthProviderFinder;
import com.hellolaw.auth.service.AuthService;
import com.hellolaw.auth.service.UserService;
import com.hellolaw.auth.util.CustomPrincipal;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final UserService userService;
	private final AuthProviderFinder authProviderFinder;

	@Value("${auth.controller.redirect-url}")
	private String OAuthRedirectURL;

	@GetMapping("/login/oauth2/code/kakao")
	ResponseEntity<ApiResponse<Void>> kakaoLogin(@RequestParam("code") String code, HttpServletResponse response) throws
		IOException {
		AuthProvider authProvider = authProviderFinder.findAuthProvider("카카오");
		return login(code, response, authProvider);
	}

	@GetMapping("/login/oauth2/code/google")
	ResponseEntity<ApiResponse<Void>> googleLogin(@RequestParam("code") String code,
		HttpServletResponse response) throws IOException {
		AuthProvider authProvider = authProviderFinder.findAuthProvider("구글");
		return login(code, response, authProvider);
	}

	private ResponseEntity<ApiResponse<Void>> login(String code, HttpServletResponse response,
		AuthProvider authProvider) throws IOException {
		String accessToken = authService.getAccessToken(code, authProvider);
		UserInfoResponse userInfo = authService.getUserInfo(accessToken, authProvider);
		log.info(userInfo.toString());
		TokenResponse tokenResponse = userService.login(userInfo, authProvider);

		Cookie cookie = new Cookie("access-token", tokenResponse.getAccess_token());
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		cookie.setPath("/");
		response.addCookie(cookie);

		Cookie nicknameCookie = new Cookie("nickname", authProvider.getUserNickname(userInfo));
		nicknameCookie.setHttpOnly(false);
		nicknameCookie.setMaxAge(60 * 60 * 24 * 30);
		nicknameCookie.setPath("/");
		response.addCookie(nicknameCookie);

		// todo 메인페이지로 변경
		response.sendRedirect(OAuthRedirectURL);
		return ResponseEntity.ok(ApiResponse.success());
	}

	@GetMapping("/logout")
	ResponseEntity<ApiResponse<Void>> logout(Authentication authentication, HttpServletResponse response) throws
		IOException {
		// 쿠키를 무효화하기 위해 최대 나이를 0으로 설정
		Cookie cookie = new Cookie("access-token", null); // 값은 null
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0); // 쿠키를 즉시 만료시킴
		cookie.setPath("/"); // 전역 경로
		response.addCookie(cookie);

		response.sendRedirect(OAuthRedirectURL); // 사용자를 로그인 페이지 또는 홈페이지로 리다이렉트

		CustomPrincipal principal = (CustomPrincipal)authentication.getPrincipal();
		userService.logout(principal.getId(), principal.getSocialId(),
			authProviderFinder.findAuthProvider(principal.getProvider()));
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok().build(); // OK 상태만 보냄
	}

	@GetMapping("/authentication")
	Long authentication(Authentication authentication) {
		CustomPrincipal principal = (CustomPrincipal)authentication.getPrincipal();
		return principal.getId();
	}
}