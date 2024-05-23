package com.hellolaw.hellolaw.internal.service;

import com.hellolaw.hellolaw.internal.dto.LawInformationDto;

public interface LawInformationService {

	public LawInformationDto getLawInformation(String lawName);
}
