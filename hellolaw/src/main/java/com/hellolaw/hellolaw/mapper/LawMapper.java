package com.hellolaw.hellolaw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hellolaw.hellolaw.dto.LawDetailResponse;
import com.hellolaw.hellolaw.dto.LawRankingResponse;
import com.hellolaw.hellolaw.entity.Law;
import com.hellolaw.hellolaw.internal.dto.LawInformationDto;

@Mapper(componentModel = "spring")
public interface LawMapper {

	LawMapper INSTANCE = Mappers.getMapper(LawMapper.class);

	@Mapping(target = "lawName", source = "name")
	@Mapping(target = "lawDetail", source = "contents")
	public LawDetailResponse toLawDetailResponse(Law law);

	@Mapping(target = "name", source = "lawName")
	public Law toLaw(LawInformationDto lawInformationDto);

	@Mapping(target = "lawId", source = "id")
	@Mapping(target = "lawName", source = "name")
	public LawRankingResponse toLawRankingResponse(Law law);
}