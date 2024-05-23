package com.hellolaw.auth.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class RedisServiceImpl implements RedisService {

	private final ReactiveRedisTemplate<String, String> redisTemplate;

	@Autowired
	public RedisServiceImpl(ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
		this.redisTemplate = reactiveRedisTemplate;
	}

	// 만료시간 설정 -> 자동 삭제
	public Mono<Boolean> setValuesWithTimeout(String key, String value, long timeout) {
		return redisTemplate.opsForValue()
			.set(key, value, Duration.ofMillis(timeout));
	}

	public Mono<String> getValues(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Transactional
	public Mono<Boolean> deleteValues(String key) {
		return redisTemplate.delete(key).map(deleted -> deleted > 0);
	}
}