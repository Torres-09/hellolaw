package com.hellolaw.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hellolaw.auth.entity.SocialProvider;
import com.hellolaw.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserBySocialProvider(SocialProvider socialProviders);
}