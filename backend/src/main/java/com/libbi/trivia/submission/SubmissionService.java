package com.libbi.trivia.submission;

public interface SubmissionService {

	SubmissionResponseDto createQandASubmission(Long roundId, Long teamId, SubmissionRequestDto submissionRequestDto);

}
