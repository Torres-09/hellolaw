package com.hellolaw.auth.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

public interface RedisService {

	void incrementValueForHash(String key, String hashKey, int value);

	void setValue(String key, String value);

	void setValueForSet(String key, String value);

	void setValueForList(String key, String value);

	void setValuesWithTimeout(String key, String value, long timeout);

	Map<String, Integer> getValuesForHash(String key);

	String getValues(String key);

	List<String> getValuesForList(String key);

	List<String> getValuesForListV2(String key);

	Set<String> getValuesForSet(String key);

	boolean existsKey(String key);

	void deleteValues(String key);

	@Transactional
	void deleteValueForList(String key);
}