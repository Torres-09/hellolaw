package com.hellolaw.auth.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.hellolaw.auth.dto.internal.GeneratedToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JWTProvider {

	@Value("${jwt.secret-key}")
	private String secretKey;

	@Value("${jwt.access-token.expiration}")
	private Long accessTokenExpirationTime;

	@Value("${jwt.refresh-token.expiration}")
	private Long refreshTokenExpirationTime;

	public String createToken(String subject, Long id, String provider, Long expirationTIme) {
		long now = System.currentTimeMillis();

		return Jwts.builder()
			.setHeaderParam("typ", "JWT")
			.setHeaderParam("alg", "HS512")
			.setSubject(subject)
			.claim("id", id)
			.claim("provider", provider)
			.setExpiration(new Date(now + expirationTIme))
			.signWith(SignatureAlgorithm.HS512, secretKey)
			.compact();
	}

	public GeneratedToken generatedToken(Long id, String provider) {
		String accessToken = createAccessToken(id, provider);
		String refreshToken = createRefreshToken(id, provider);
		return new GeneratedToken("Bearer", accessToken, refreshToken);
	}

	public String createAccessToken(Long id, String provider) {
		return createToken("access-token", id, provider, accessTokenExpirationTime);
	}

	public String createRefreshToken(Long id, String provider) {
		return createToken("refresh-token", id, provider, refreshTokenExpirationTime);
	}

	public Long getId(String token) {
		try {
			return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.get("id", Long.class);
		} catch (ExpiredJwtException e) {
			return e.getClaims().get("id", Long.class);
		}
	}

	public String getProvider(String token) {
		try {
			return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.get("provider", String.class);
		} catch (ExpiredJwtException e) {
			return e.getClaims().get("provider", String.class);
		}
	}

	public boolean isValidateToken(String token) {
		log.info("토큰검증");
		try {
			Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
			return true;
		} catch (ExpiredJwtException e) {
			log.error("Token has expired", e);
		} catch (MalformedJwtException e) {
			log.error("Token is malformed", e);
		} catch (SignatureException e) {
			log.error("Token signature is invalid", e);
		} catch (Exception e) {
			log.error("Token validation error", e);
		}
		return false;
	}

	public Claims getClaims(String token) {
		try {
			return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}

	public long getTokenExpirationTime(String token) {
		try {
			return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()
				.getTime();
		} catch (ExpiredJwtException e) {
			throw new RuntimeException();
		}
	}

	public Authentication getAuthentication(String token) {
		Long id = getId(token);
		Collection<GrantedAuthority> authorities = Collections.singleton((new SimpleGrantedAuthority("USER")));
		return new UsernamePasswordAuthenticationToken(id, null, authorities);
	}
}