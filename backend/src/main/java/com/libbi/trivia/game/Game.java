package com.libbi.trivia.game;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

import com.libbi.trivia.team.Team;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Game {
	@Id
	@SequenceGenerator(
	        name = "game_sequence",
	        sequenceName = "game_sequence",
	        allocationSize = 1
	)
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "game_sequence"
	)
	private Long id;
	
	@OneToMany(mappedBy = "game")
	private List<Team> teams;
	
//	@OneToMany(mappedBy = "game")
//	private List<Round> rounds;
	private LocalDateTime date;
	private String location;
	private boolean deleted = false;
}