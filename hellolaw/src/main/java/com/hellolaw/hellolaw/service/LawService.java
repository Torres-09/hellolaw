package com.hellolaw.hellolaw.service;

import java.io.IOException;
import java.util.List;

import com.hellolaw.hellolaw.dto.LawDetailResponse;
import com.hellolaw.hellolaw.entity.Category;

public interface LawService {

	LawDetailResponse getLawDetail(String lawName);

	List<?> getLawRanking(String category);
}
