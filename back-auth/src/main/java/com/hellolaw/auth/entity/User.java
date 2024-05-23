package com.hellolaw.auth.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "user")
public class User extends BaseEntity {
	@Column(name = "nickname")
	private String nickname;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private SocialProvider socialProvider;
}