package com.libbi.trivia.round;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoundServiceImpl implements RoundService{

	private final RoundRepository roundRepository;
	private final RoundMapper roundMapper;
	




	@Override
	public RoundResponseDto getRound(Long gameId, Integer roundNumber) {
		return roundMapper.entityToDto(roundRepository.findByGameIdAndRoundNumber(gameId, roundNumber));
		
	}

}
