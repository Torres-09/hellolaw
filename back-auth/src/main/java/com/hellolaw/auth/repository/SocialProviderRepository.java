package com.hellolaw.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hellolaw.auth.entity.SocialProvider;

@Repository
public interface SocialProviderRepository extends JpaRepository<SocialProvider, Long> {
	Optional<SocialProvider> findSocialProviderBySocialId(String socialId);
}
