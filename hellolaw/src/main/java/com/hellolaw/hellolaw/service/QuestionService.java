package com.hellolaw.hellolaw.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hellolaw.hellolaw.dto.QuestionAnswerResponse;
import com.hellolaw.hellolaw.dto.QuestionHistoryResponse;
import com.hellolaw.hellolaw.dto.QuestionRequest;

public interface QuestionService {

	QuestionAnswerResponse generateAnswer(Long userId, QuestionRequest questionRequest) throws JsonProcessingException;

	List<QuestionHistoryResponse> getTwoQuestionHistoryList(Long userId);

	Void deleteQuestion(Long userId, Long questionId);
}
