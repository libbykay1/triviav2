package com.libbi.trivia.round;

public interface RoundService {

	RoundResponseDto getRound(Long gameId, Integer roundNumber);

	Round getRoundById(Long id);

}
