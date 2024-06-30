package com.libbi.trivia.submission;

import org.springframework.stereotype.Service;


import com.libbi.trivia.round.Round;
import com.libbi.trivia.round.RoundService;
import com.libbi.trivia.team.Team;
import com.libbi.trivia.team.TeamService;
import com.libbi.trivia.exceptions.BadRequestException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService{
	
	private final RoundService roundService;
	private final TeamService teamService;
	private final SubmissionMapper submissionMapper;
	private final SubmissionRepository submissionRepository;
	
	private boolean alreadySubmitted(Round round, Team team) {
		for (Submission submission : round.getSubmissions()) {
			if (submission.getTeam().equals(team)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public SubmissionResponseDto createQandASubmission(Long roundId, Long teamId,
			SubmissionRequestDto submissionRequestDto) {
		Round round = roundService.getRoundById(roundId);
		Team team = teamService.getTeamById(teamId);
		Submission submission = submissionMapper.requestDtoToEntity(submissionRequestDto);
		submission.setRound(round);
		submission.setTeam(team);
		if (alreadySubmitted(round, team)) {
			throw new BadRequestException(
					"Team with id: " + teamId + " has already submitted for round with id: " + roundId);
		}
		return submissionMapper.entityToDto(submissionRepository.saveAndFlush(submission));
	}

}
