package com.libbi.trivia.submission;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/submission")
public class SubmissionController {
	
	private final SubmissionService submissionService;
	
	@PostMapping("{roundId}/{teamId}")
	public SubmissionResponseDto createQandASubmission(@PathVariable Long roundId, @PathVariable Long teamId, @RequestBody SubmissionRequestDto submissionRequestDto) {
		return submissionService.createQandASubmission(roundId, teamId, submissionRequestDto);
	}

}
