package com.libbi.trivia.round;

import java.util.List;

import com.libbi.trivia.game.Game;
import com.libbi.trivia.question.Question;
import com.libbi.trivia.submission.Submission;
import com.libbi.trivia.user.User;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Round {
	@Id
	@SequenceGenerator(
	        name = "round_sequence",
	        sequenceName = "round_sequence",
	        allocationSize = 1
	)
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "round_sequence"
	)
	private Long id;
	
	private Integer roundNumber;
	
	private String title;
	
	private String prompt;
	
	@OneToMany(mappedBy = "round")
	private List<Question> questions;
	
	@OneToMany(mappedBy = "round")
	private List<Submission> submissions;
	
	private boolean visible = false;
	
	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;
	
	private boolean deleted = false;
	
	private User author;
	
	private String category;
	
	private String type;
	
	private boolean published;
	
	@ElementCollection
    @CollectionTable(name = "round_tags", joinColumns = @JoinColumn(name = "round_id"))
    @Column(name = "tag")
	private List<String> tags;
}
