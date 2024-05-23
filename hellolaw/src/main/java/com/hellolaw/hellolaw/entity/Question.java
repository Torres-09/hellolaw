package com.hellolaw.hellolaw.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "question")
public class Question extends BaseEntity {

	@Column(name = "contents", columnDefinition = "TEXT")
	private String contents;

	@Column(name = "victim")
	private boolean victim;

	@Column(name = "category")
	private String category;

	@OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
	private Answer answer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
