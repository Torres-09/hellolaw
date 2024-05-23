package com.hellolaw.auth.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.hellolaw.auth.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

	Mono<User> findUserByEmail(String email);

	@NonNull
	Flux<User> findAllById(@NonNull Iterable<Long> userIds);
}
