package com.libbi.trivia.answer;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerResponseDto {
	private Long id;
	private List<String> correct;
	private Double numberOfAnswers;
	private Double availablePoints;

}
