package com.hellolaw.auth.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public void incrementValueForHash(String key, String hashKey, int value) {
		redisTemplate.opsForHash().increment(key, hashKey, value);

	}

	@Override
	public void setValue(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void setValueForSet(String key, String value) {
		redisTemplate.opsForSet().add(key, value);
	}

	@Override
	public void setValueForList(String key, String value) {
		redisTemplate.opsForList().rightPush(key, value);
	}

	// 만료시간 설정 -> 자동 삭제
	@Override
	public void setValuesWithTimeout(String key, String value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
	}

	@Override
	public Map<String, Integer> getValuesForHash(String key) {
		Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
		Map<String, Integer> resultMap = (Map<String, Integer>)(Map<?, ?>)map;
		return resultMap;
	}

	@Override
	public String getValues(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public List<String> getValuesForList(String key) {
		long size = redisTemplate.opsForList().size(key);
		long start = Math.max(0, size - 5);
		return redisTemplate.opsForList().range(key, start, -1);
	}

	@Override
	public List<String> getValuesForListV2(String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	@Override
	public Set<String> getValuesForSet(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	public boolean existsKey(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	@Transactional
	public void deleteValues(String key) {
		redisTemplate.delete(key);
	}

	@Override
	@Transactional
	public void deleteValueForList(String key) {
		redisTemplate.opsForList().leftPop(key);
	}
}