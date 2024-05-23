package com.hellolaw.hellolaw.service;

import org.springframework.stereotype.Service;

import com.hellolaw.hellolaw.dto.PrecedentDetailResponse;
import com.hellolaw.hellolaw.entity.Precedent;
import com.hellolaw.hellolaw.repository.PrecedentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PrecedentServiceImpl implements PrecedentService {

	private final PrecedentRepository precedentRepository;

	@Override
	public PrecedentDetailResponse getPrecedentDetail(Long precedentId) {
		Precedent precedent = precedentRepository.findById(precedentId).orElseThrow();
		return PrecedentDetailResponse.create(precedent);
	}
}
