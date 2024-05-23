package com.hellolaw.hellolaw.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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
@Table(name = "answer")
public class Answer extends BaseEntity {

	@Column(name = "contents", columnDefinition = "TEXT")
	private String contents;

	@OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
	private List<RelatedAnswer> relatedAnswers;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;

	@OneToOne(mappedBy = "answer", cascade = CascadeType.ALL)
	private SummaryAnswer summaryAnswer;

}
