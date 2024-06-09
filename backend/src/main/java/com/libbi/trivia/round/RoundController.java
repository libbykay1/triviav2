package com.libbi.trivia.round;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/round")
public class RoundController {
	
	private final RoundService roundService;

	@GetMapping("{gameId}/{roundNumber}")
	public RoundResponseDto getRound(@PathVariable Long gameId, @PathVariable Integer roundNumber) {
		return roundService.getRound(gameId, roundNumber);
	}

}
