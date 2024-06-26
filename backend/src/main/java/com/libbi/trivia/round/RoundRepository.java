package com.libbi.trivia.round;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long>{

	Round findByGameIdAndRoundNumber(Long gameId, Integer roundNumber);

	Round findByIdAndDeletedFalse(Long id);

}
