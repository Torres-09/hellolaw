package com.hellolaw.hellolaw.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "related_answer")
public class RelatedAnswer extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "answer_id")
	private Answer answer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "precent_id")
	private Precedent precedent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "law_id")
	private Law law;

}
