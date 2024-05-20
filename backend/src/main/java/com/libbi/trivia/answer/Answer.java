package com.libbi.trivia.answer;

import java.util.List;

import com.libbi.trivia.question.Question;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Answer {
	@Id
	@SequenceGenerator(
	        name = "answer_sequence",
	        sequenceName = "answer_sequence",
	        allocationSize = 1
	)
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "answer_sequence"
	)
	private Long id;
	
	private Double availablePoints;
	@OneToOne
    @JoinColumn(name = "question_id")
	private Question question;
	
	private List<String> correct;
	
	private List<String> choices;
	
	private Integer numberOfAnswers;
	
	private boolean deleted;
	
	

}
