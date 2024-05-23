package com.hellolaw.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "social_provider")
public class SocialProvider extends BaseEntity {
	@Column(name = "social_id")
	private String socialId;

	@Column(name = "provider")
	private String provider;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;
}