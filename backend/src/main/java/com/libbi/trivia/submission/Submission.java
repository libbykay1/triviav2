package com.libbi.trivia.submission;

import java.util.List;

import com.libbi.trivia.round.Round;
import com.libbi.trivia.team.Team;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Submission {
	@Id
	@SequenceGenerator(
	        name = "submission_sequence",
	        sequenceName = "submission_sequence",
	        allocationSize = 1
	)
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "submission_sequence"
	)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name = "round_id")
	private Round round;
	
	@ElementCollection
    @CollectionTable(name = "submitted_answers", joinColumns = @JoinColumn(name = "submission_id"))
    @Column(name = "submittedAnswers")
	private List<String> submittedAnswers;
	
	private boolean doubleOrNothing;
	
	private Double points;
	
	private boolean deleted = false;
	
	public boolean getDoubleOrNothing() {
		return doubleOrNothing;
	}
	
	public void setDoubleOrNothing(boolean doubleOrNothing) {
		this.doubleOrNothing = doubleOrNothing;
	}

}
