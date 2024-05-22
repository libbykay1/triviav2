package com.libbi.trivia.team;

public interface TeamService {
	
	TeamResponseDto createTeam(TeamRequestDto teamRequestDto, Long gameId);

	TeamResponseDto joinGame(Long teamId, Long gameId);

	TeamResponseDto changeTeamName(TeamRequestDto teamRequestDto, Long teamid);

}
