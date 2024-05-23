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

import com.hellolaw.auth.dto.ErrorBase;
import com.hellolaw.auth.exception.InvalidException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

	public String createToken(String subject, Long id, String socialId, String provider, Long expirationTIme) {
		long now = System.currentTimeMillis();

		return Jwts.builder()
			.setHeaderParam("typ", "JWT")
			.setHeaderParam("alg", "HS512")
			.setSubject(subject)
			.claim("id", id)
			.claim("socialId", socialId)
			.claim("provider", provider)
			.setExpiration(new Date(now + expirationTIme))
			.signWith(SignatureAlgorithm.HS512, secretKey)
			.compact();
	}

	public String createAccessToken(Long id, String socialId, String provider) {
		return createToken("access", id, socialId, provider, accessTokenExpirationTime);
	}

	public String createRefreshToken(Long id, String socialId, String provider) {
		return createToken("refresh", id, socialId, provider, refreshTokenExpirationTime);
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

	public String getSocialId(String token) {
		try {
			return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.get("socialId", String.class);
		} catch (ExpiredJwtException e) {
			throw new InvalidException(ErrorBase.E400_INVALID_TOKEN);
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
			throw new InvalidException(ErrorBase.E400_INVALID_TOKEN);
		}
	}

	public boolean isValidateToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
			return true;
		} catch (Exception e) {
			return false;
		}
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
			throw new InvalidException(ErrorBase.E400_INVALID_TOKEN);
		}
	}

	public Authentication getAuthentication(String token) {
		Long id = getId(token);
		String socialId = getSocialId(token);
		String provider = getProvider(token);
		CustomPrincipal principal = new CustomPrincipal(id, socialId, provider);
		Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		return new UsernamePasswordAuthenticationToken(principal, null, authorities);
	}
}