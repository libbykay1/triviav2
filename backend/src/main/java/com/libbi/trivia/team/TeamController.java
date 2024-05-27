package com.libbi.trivia.team;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team")
public class TeamController {
	private final TeamService teamService;

	@PostMapping("{gameId}/new")
	public TeamResponseDto createTeam(@RequestBody TeamRequestDto teamRequestDto, @PathVariable Long gameId) {
		return teamService.createTeam(teamRequestDto, gameId);
	}

	@PatchMapping("{teamId}/{gameId}/join")
	public TeamResponseDto joinGame(@PathVariable Long teamId, @PathVariable Long gameId) {
		return teamService.joinGame(teamId, gameId);
	}

	@PatchMapping("{teamId}/changename")
	public TeamResponseDto changeTeamName(@RequestBody TeamRequestDto teamRequestDto, @PathVariable Long teamId) {
		return teamService.changeTeamName(teamRequestDto, teamId);
	}
	
	@GetMapping("{userId}/team")
	public TeamResponseDto getUserTeam(@PathVariable Integer userId) {
		return teamService.getUserTeam(userId);
	}

}
