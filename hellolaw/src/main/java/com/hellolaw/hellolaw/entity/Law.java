package com.hellolaw.hellolaw.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "law")
public class Law extends BaseEntity {

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Setter
	@Column(name = "contents", columnDefinition = "TEXT")
	private String contents;

	@Setter
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private Category category;

	@Setter
	@Column(name = "count")
	@ColumnDefault("0")
	private Long count;

	@OneToMany(mappedBy = "law", cascade = CascadeType.ALL)
	private List<RelatedAnswer> relatedAnswers;

	@Builder
	public Law(String name, String contents, Category category) {
		this.name = name;
		this.contents = contents;
		this.category = category;
		this.count = 1L;
	}
}
