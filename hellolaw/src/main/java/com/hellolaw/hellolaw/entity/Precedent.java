package com.hellolaw.hellolaw.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "precedent")
public class Precedent extends BaseEntity {

	@Column(name = "case_no", length = 30)
	private String caseNo;

	@Column(name = "judmn_adju_de")
	private LocalDateTime judgementDate;

	@Column(name = "court_nm")
	private String courtName;

	@Column(name = "case_nm")
	private String caseName;

	@Column(name = "disposal_content", columnDefinition = "TEXT")
	private String disposal;

	@Column(name = "case_field")
	private Long caseField;

	@Column(name = "detail_field")
	private Long detailField;

	@Column(name = "trail_field")
	private Long trailField;

	@Column(name = "relate_laword", columnDefinition = "TEXT")
	private String relateLaword;

	@Column(name = "basic_fact", columnDefinition = "TEXT")
	private String basicFact;

	@Column(name = "conclusion", columnDefinition = "TEXT")
	private String conclusion;

	@Column(name = "court_dcss", columnDefinition = "TEXT")
	private String courtDcss;

	@OneToMany(mappedBy = "precedent")
	private List<RelatedAnswer> relatedAnswers;

}
