package com.libbi.trivia.question;

import org.mapstruct.Mapper;

import com.libbi.trivia.answer.AnswerMapper;

@Mapper(componentModel = "spring", uses = { AnswerMapper.class })
public interface QuestionMapper {

}
