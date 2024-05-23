package com.hellolaw.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Getter;

@Table("user")
@Getter
@Builder
public class User {

	@Id
	@Column("id")
	private Long id;

	@Column("nickname")
	private String name;

	@Column("email")
	private String email;

	@Column("provider")
	private String provider;

	private String role;
}
