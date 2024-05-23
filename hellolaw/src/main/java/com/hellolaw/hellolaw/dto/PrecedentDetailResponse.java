package com.hellolaw.hellolaw.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.hellolaw.hellolaw.entity.Precedent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrecedentDetailResponse {
	/*
	case_no,judmn_adju_de,
	court_nm,case_nm,
	case_field,detail_field,trail_field,
	relate_laword,disposal_content,
	basic_fact,court_dcss,conclusion
	 */
	private String caseNo;
	private LocalDate judmnAdjuDe;
	private String courtNm;
	private String caseNnm;
	private Long caseField;
	private Long detailField;
	private Long trailField;
	private List<String> relateLaword;
	private String disposalContent;
	private String basicFact;
	private String courtDcss;
	private String conclusion;

	public static PrecedentDetailResponse create(Precedent precedent) {
		return PrecedentDetailResponse.builder()
			.caseNo(precedent.getCaseNo())
			.judmnAdjuDe(precedent.getJudgementDate().toLocalDate())
			.courtNm(precedent.getCourtName())
			.caseField(precedent.getCaseField())
			.detailField(precedent.getDetailField())
			.relateLaword(
				List.of(Arrays.stream(precedent.getRelateLaword().split(",")).map(String::trim).toArray(String[]::new)))
			.disposalContent(precedent.getDisposal())
			.basicFact(precedent.getBasicFact())
			.courtDcss(precedent.getCourtDcss())
			.conclusion(precedent.getConclusion())
			.build();
	}
}
