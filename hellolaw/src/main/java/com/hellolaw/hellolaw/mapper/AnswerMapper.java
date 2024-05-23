package com.hellolaw.hellolaw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hellolaw.hellolaw.entity.Answer;
import com.hellolaw.hellolaw.entity.Question;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

	@Mapping(target = "contents", source = "contents")
	@Mapping(target = "question", source = "question")
	Answer toAnswer(String contents, Question question);
}