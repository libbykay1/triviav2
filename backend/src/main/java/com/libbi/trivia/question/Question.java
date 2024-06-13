package com.libbi.trivia.question;

import com.libbi.trivia.answer.Answer;
import com.libbi.trivia.round.Round;
import com.libbi.trivia.user.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Question {

	@Id
	@SequenceGenerator(
	        name = "question_sequence",
	        sequenceName = "question_sequence",
	        allocationSize = 1
	)
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "question_sequence"
	)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "round_id")
	private Round round;
	
	private String text;
	
	@Column(name = "correct_answer", columnDefinition = "TEXT")
	private String correctAnswer;
	
	private String imageUrl;
	
    @Enumerated(EnumType.STRING)
    private Type type;
	
	private boolean deleted = false;	
	
	@OneToOne
    @JoinColumn(name = "answer_id")
	private Answer answer;
	
}