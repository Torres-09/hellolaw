package com.hellolaw.hellolaw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellolaw.hellolaw.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByNickname(String nickname);
}
