package com.hellolaw.hellolaw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hellolaw.hellolaw.dto.QuestionRequest;
import com.hellolaw.hellolaw.entity.Question;
import com.hellolaw.hellolaw.entity.User;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

	QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

	@Mapping(target = "contents", source = "questionRequest.question")
	@Mapping(target = "user", source = "user")
	Question toQuestion(QuestionRequest questionRequest, User user);
}