package com.libbi.trivia.team;

import org.mapstruct.Mapper;

import com.libbi.trivia.user.UserMapper;


@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TeamMapper {

	Team requestDtoToEntity(TeamRequestDto teamRequestDto);

	TeamResponseDto entityToResponseDto(Team entity);

}