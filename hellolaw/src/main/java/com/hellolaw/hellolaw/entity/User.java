package com.hellolaw.hellolaw.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User extends BaseEntity {
	@Column(name = "nickname")
	private String nickname;

	@OneToMany(mappedBy = "user")
	private List<Question> question;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private SocialProvider socialProvider;

}
