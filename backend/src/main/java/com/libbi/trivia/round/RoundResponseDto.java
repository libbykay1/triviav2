package com.libbi.trivia.round;

import java.util.List;

import com.libbi.trivia.question.QuestionResponseDto;
import com.libbi.trivia.question.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoundResponseDto {
	
	private Long id;
	private Long gameId;
	private Integer roundNumber;
	private String title;
	private String prompt;
	private boolean visible;
	private boolean published;
	private String category;
	private List<String> tags;
	private List<QuestionResponseDto> questions;

}
