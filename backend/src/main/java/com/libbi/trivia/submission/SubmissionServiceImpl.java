package com.libbi.trivia.submission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.libbi.trivia.round.Round;
import com.libbi.trivia.round.RoundService;
import com.libbi.trivia.team.Team;
import com.libbi.trivia.team.TeamService;
import com.libbi.trivia.answer.Answer;
import com.libbi.trivia.exceptions.BadRequestException;
import com.libbi.trivia.question.Question;

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
	
	private boolean verifyAnswer(String submittedAnswer, List<String> acceptableAnswers) {
		for (int i = 0; i < acceptableAnswers.size(); i++) {
			if (submittedAnswer.toLowerCase().contains(acceptableAnswers.get(i).toLowerCase())) {
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

	    Double points = 0.0;
	    List<String> submittedAnswers = submissionRequestDto.getSubmittedAnswers();
	    Map<Integer, Map<Double, List<String>>> correctAnswers = new HashMap<>();

	    int questionIndex = 0;
	    for (Question question : round.getQuestions()) {
	        Answer answer = question.getAnswer();
	        Map<Double, List<String>> answerMap = new HashMap<>();
	        for (int i = 0; i < answer.getNumberOfAnswers(); i++) {
	            answerMap.put(answer.getAvailablePoints() / answer.getNumberOfAnswers(), answer.getCorrect());
	        }
	        correctAnswers.put(questionIndex++, answerMap);
	    }

	    questionIndex = 0;
	    for (Map.Entry<Integer, Map<Double, List<String>>> entry : correctAnswers.entrySet()) {
	        Map<Double, List<String>> answerMap = entry.getValue();
	        for (Map.Entry<Double, List<String>> answerEntry : answerMap.entrySet()) {
	            Double pointsPerAnswer = answerEntry.getKey();
	            List<String> acceptableAnswers = answerEntry.getValue();
	            String submittedAnswer = submittedAnswers.get(questionIndex);
	            if (verifyAnswer(submittedAnswer, acceptableAnswers)) {
	                points += pointsPerAnswer;
	            }
	        }
	        questionIndex++;
	    }

	    submission.setPoints(points);
	    return submissionMapper.entityToDto(submissionRepository.saveAndFlush(submission));
	}


}
