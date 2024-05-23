package com.hellolaw.hellolaw.interceptor;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
	@Value("${my.filters.url-patterns}")
	private List<String> urlPatterns;

	@Value("${hellolaw.auth.url}")
	private String authUrl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {

		String cookieValue = getCookieValue(request, "access-token");
		Long userId;

		if (cookieValue == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No access-token found");
			return true;
		}

		try {
			WebClient webClient = WebClient.create();
			log.info(authUrl);
			userId = webClient
				.get()
				.uri(authUrl)
				.header("Cookie", "access-token=" + cookieValue)
				.retrieve()
				.bodyToMono(Long.class)
				.block();

			if (userId == null || userId <= 0) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid User ID");
				return true;
			}
		} catch (ResponseStatusException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Authentication server error");
			return true;
		}

		request.setAttribute("userId", userId);
		return true;
	}

	private static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}
