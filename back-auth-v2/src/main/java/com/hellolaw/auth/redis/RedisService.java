package com.hellolaw.auth.redis;

import reactor.core.publisher.Mono;

public interface RedisService {

	Mono<Boolean> setValuesWithTimeout(String key, String value, long timeout);

	Mono<String> getValues(String key);

	Mono<Boolean> deleteValues(String key);

}