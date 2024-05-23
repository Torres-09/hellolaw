package com.hellolaw.hellolaw.service;

import com.hellolaw.hellolaw.dto.PrecedentDetailResponse;

public interface PrecedentService {
	PrecedentDetailResponse getPrecedentDetail(Long precedentId);
}
