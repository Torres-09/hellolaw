package com.hellolaw.hellolaw.internal.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecedentDto {
	private Long index;
	private String case_no;
	private String judmn_adju_de;
	private String court_nm;
	private String case_nm;
	private Integer case_field;
	private Integer detail_field;
	private Integer trail_field;
	private List<String> relate_laword;
	private String disposal_content;
	private String basic_fact;
	private String court_dcss;
	private String conclusion;
}
