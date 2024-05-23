package com.hellolaw.hellolaw.internal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hellolaw.hellolaw.internal.dto.PrecedentDto;
import com.hellolaw.hellolaw.internal.dto.SuggestionDto;

public interface BERTService {

	public PrecedentDto getSimilarPrecedent(String question) throws JsonProcessingException;

	public SuggestionDto getSuggestion(String text) throws JsonProcessingException;
}
