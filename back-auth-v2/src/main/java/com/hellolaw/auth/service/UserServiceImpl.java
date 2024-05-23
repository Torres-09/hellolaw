package com.hellolaw.auth.service;

import org.springframework.stereotype.Service;

import com.hellolaw.auth.redis.RedisService;
import com.hellolaw.auth.repository.UserRepository;
import com.hellolaw.auth.util.JWTProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final RedisService redisService;

	@Override
	public void logout(Long id) {
		redisService.deleteValues("RT:".concat(String.valueOf(id)));
	}
}