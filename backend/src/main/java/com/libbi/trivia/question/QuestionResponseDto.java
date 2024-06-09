package com.libbi.trivia.question;

import com.libbi.trivia.answer.AnswerResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionResponseDto {
	
	private Long id;
	private String text;
	private String correctAnswer;
	private Type type;
	private String imageUrl;
	private AnswerResponseDto answer;

}
