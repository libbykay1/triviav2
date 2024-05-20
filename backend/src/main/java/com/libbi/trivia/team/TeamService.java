package com.libbi.trivia.team;

public interface TeamService {
	
	TeamResponseDto createTeam(TeamRequestDto teamRequestDto, Long gameId);

}
