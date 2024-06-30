package com.libbi.trivia.submission;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionRequestDto {
	private List<String> submittedAnswers;
	private boolean doubleOrNothing;
	

}
