package com.libbi.trivia.round;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.libbi.trivia.question.QuestionMapper;

@Mapper(componentModel= "spring", uses = { QuestionMapper.class })
public interface RoundMapper {

	@Mapping(source = "game.id", target = "gameId")	
	RoundResponseDto entityToDto(Round round);

}
