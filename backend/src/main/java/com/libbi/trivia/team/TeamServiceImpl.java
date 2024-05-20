package com.libbi.trivia.team;

import org.springframework.stereotype.Service;

import com.libbi.trivia.game.Game;
import com.libbi.trivia.game.GameService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

	private final TeamMapper teamMapper;
	private final TeamRepository teamRepository;
	private final GameService gameService;
	
	@Override
	public TeamResponseDto createTeam(TeamRequestDto teamRequestDto, Long gameId) {
		Team team = teamMapper.requestDtoToEntity(teamRequestDto);
		Game game = gameService.getGameById(gameId);
		team.setGame(game);
		
		return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(team));
	
	}

	

}

