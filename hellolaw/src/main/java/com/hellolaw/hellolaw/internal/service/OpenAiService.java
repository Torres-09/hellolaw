package com.hellolaw.hellolaw.internal.service;

import com.hellolaw.hellolaw.internal.dto.PrecedentSummaryResponse;

public interface OpenAiService {

	public PrecedentSummaryResponse getBasicFactInformation(String disposal, String basicFact);
}
