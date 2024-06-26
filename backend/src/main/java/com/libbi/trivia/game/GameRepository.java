package com.libbi.trivia.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	Game findByIdAndDeletedFalse(Long gameId);

}
