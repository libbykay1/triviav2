package com.libbi.trivia.submission;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponseDto {
	
	private Long id;
	private Long teamId;
	private Long roundId;
	private List<String> submittedAnswers;
	private boolean doubleOrNothing;
	private Double points;

}
