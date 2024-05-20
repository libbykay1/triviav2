package com.libbi.trivia.team;

import java.util.List;

import com.libbi.trivia.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

import com.libbi.trivia.game.Game;
import com.libbi.trivia.submission.Submission;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Team {
	@Id
	@SequenceGenerator(
	        name = "team_sequence",
	        sequenceName = "team_sequence",
	        allocationSize = 1
	)
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "team_sequence"
	)
	private Long id;
	
	private String teamName;
	
	@OneToMany(mappedBy = "team")
	private List<User> members;
	
	@OneToMany(mappedBy = "team")
	private List<Submission> submissions;
	
	private boolean deleted = false;

	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;
	private Double points = 0.0;
	

}
