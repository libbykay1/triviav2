package com.libbi.trivia.team;

import org.springframework.stereotype.Service;

import com.libbi.trivia.game.Game;
import com.libbi.trivia.game.GameService;
import com.libbi.trivia.user.User;
import com.libbi.trivia.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

	private final TeamMapper teamMapper;
	private final TeamRepository teamRepository;
	private final GameService gameService;
	private final UserRepository userRepository;
	
	@Override
	public TeamResponseDto createTeam(TeamRequestDto teamRequestDto, Long gameId) {
		Team team = teamMapper.requestDtoToEntity(teamRequestDto);
		Game game = gameService.getGameById(gameId);
		team.setGame(game);
		
		return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(team));
	
	}

	@Override
	public TeamResponseDto joinGame(Long teamId, Long gameId) {
		Team team = getTeambyId(teamId);
		Game game = gameService.getGameById(gameId);
		team.setGame(game);
		
		return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(team));
	}

	private Team getTeambyId(Long teamId) {
		return teamRepository.findByIdAndDeletedFalse(teamId);
	}

	@Override
	public TeamResponseDto changeTeamName(TeamRequestDto teamRequestDto, Long teamId) {
		Team team = getTeambyId(teamId);
		team.setTeamName(teamRequestDto.getTeamName());
		
		return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(team));

	}

	@Override
	public TeamResponseDto getUserTeam(Integer userId) {
		User user = userRepository.getUserByIdAndDeletedFalse(userId);
		Team team = user.getTeam();
		return teamMapper.entityToResponseDto(team);
	}



	

}

