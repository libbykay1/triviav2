package com.libbi.trivia.team;

import java.util.List;

public interface TeamService {
	
	TeamResponseDto createTeam(TeamRequestDto teamRequestDto, Long gameId);

	TeamResponseDto joinGame(Long teamId, Long gameId);

	TeamResponseDto changeTeamName(TeamRequestDto teamRequestDto, Long teamid);

	TeamResponseDto getUserTeam(Integer userId);
	
	Team getTeamById(Long teamId);

	TeamResponseDto createTeamLoggedIn(TeamRequestDto teamRequestDto);

	List<TeamResponseDto> getAllGameTeams(Long gameId);

	TeamResponseDto getTeam(Long teamId);

}
