package com.hellolaw.hellolaw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hellolaw.hellolaw.entity.Answer;
import com.hellolaw.hellolaw.entity.SummaryAnswer;

@Mapper(componentModel = "spring")
public interface SummaryAnswerMapper {

	@Mapping(target = "contents", source = "contents")
	@Mapping(target = "answer", source = "answer")
	SummaryAnswer toSummaryAnswer(String contents, Answer answer);
}
