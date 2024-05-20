package com.libbi.trivia.game;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{

	private final GameRepository gameRepository;

	@Override
	public Game getGameById(Long gameId) {
		return gameRepository.findByIdAndDeletedFalse(gameId);
	}

}
