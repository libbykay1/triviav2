package com.libbi.trivia.team;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.libbi.trivia.game.Game;
import com.libbi.trivia.game.GameService;
import com.libbi.trivia.user.User;
import com.libbi.trivia.user.UserRepository;
import com.libbi.trivia.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

	private final TeamMapper teamMapper;
	private final TeamRepository teamRepository;
	private final GameService gameService;
	private final UserRepository userRepository;
	private final UserService userService;
	
	@Override
	public TeamResponseDto createTeam(TeamRequestDto teamRequestDto, Long gameId) {
		Team team = teamMapper.requestDtoToEntity(teamRequestDto);
		if (gameId != null) {
	        Game game = gameService.getGameById(gameId);
	        team.setGame(game);
	    }

		
		return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(team));
	
	}
	
	@Override
	public TeamResponseDto createTeamLoggedIn(TeamRequestDto teamRequestDto) {
		Team team = teamMapper.requestDtoToEntity(teamRequestDto);
		User currentUser = userService.getCurrentUser();
        team.setOwner(currentUser);
		
		return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(team));
	
	}

	@Override
	public TeamResponseDto joinGame(Long teamId, Long gameId) {
		Team team = getTeamById(teamId);
		Game game = gameService.getGameById(gameId);
		team.setGame(game);
		
		
		return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(team));
	}

	@Override
	public Team getTeamById(Long teamId) {
		return teamRepository.findByIdAndDeletedFalse(teamId);
	}

	@Override
	public TeamResponseDto changeTeamName(TeamRequestDto teamRequestDto, Long teamId) {
		Team team = getTeamById(teamId);
		team.setTeamName(teamRequestDto.getTeamName());
		
		
		
		return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(team));

	}

	@Override
	public TeamResponseDto getUserTeam(Integer userId) {
		User user = userRepository.getUserByIdAndDeletedFalse(userId);
		Team team = user.getTeam();
		return teamMapper.entityToResponseDto(team);
	}

	@Override
	public List<TeamResponseDto> getAllGameTeams(Long gameId) {
		Game game = gameService.getGameById(gameId);
		List<TeamResponseDto> response = new ArrayList<>();
		for (Team team : game.getTeams()) {
			TeamResponseDto dto = teamMapper.entityToResponseDto(team);
			response.add(dto);
		}
		return response;
	}



	

}

