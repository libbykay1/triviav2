package com.libbi.trivia;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.libbi.trivia.game.GameRepository;
import com.libbi.trivia.question.QuestionRepository;
import com.libbi.trivia.round.RoundRepository;
import com.libbi.trivia.submission.SubmissionRepository;
//import com.libbi.trivia.question.QuestionRepository;
//import com.libbi.trivia.round.RoundRepository;
//import com.libbi.trivia.submission.SubmissionRepository;
import com.libbi.trivia.team.TeamRepository;
import com.libbi.trivia.user.User;
import com.libbi.trivia.user.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.libbi.trivia.user.Role.PLAYER;
import static com.libbi.trivia.user.Role.QUIZMASTER;

import com.libbi.trivia.game.Game;
import com.libbi.trivia.question.Question;
import com.libbi.trivia.round.Round;
//import com.libbi.trivia.submission.Submission;
import com.libbi.trivia.team.Team;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
	
	private final RoundRepository roundRepository;
	private final TeamRepository teamRepository;
	private final QuestionRepository questionRepository;
	private final SubmissionRepository submissionRepository;
	private final GameRepository gameRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception{
		
		Game exampleGame1 = new Game();
		exampleGame1.setDate(LocalDateTime.of(2024, 2, 27, 0, 0, 0));
		exampleGame1.setLocation("The Green Dragon");
		
		Game exampleGame2 = new Game();
		exampleGame2.setDate(LocalDateTime.of(2024, 3, 5, 0, 0, 0));
		exampleGame2.setLocation("The Green Dragon");
		
		gameRepository.saveAll(Arrays.asList(new Game[] { exampleGame1, exampleGame2 }));
		
		Team exampleTeam = new Team();
		exampleTeam.setTeamName("The Fellowship of the Ring");
		exampleTeam.setDeleted(false);
		exampleTeam.setGame(exampleGame1);
		exampleTeam.setPoints(0.0);
		teamRepository.saveAll(Arrays.asList(new Team[] { exampleTeam }));
		
		User exampleUser = new User();
		exampleUser.setEmail("frodo@shire.com");
		exampleUser.setPassword(passwordEncoder.encode("ringbearer"));
		exampleUser.setTeam(exampleTeam);
		exampleUser.setRole(PLAYER);
		exampleUser.setDeleted(false);
		
		User exampleQuizmaster = new User();
		exampleQuizmaster.setEmail("gandalf@wizard.com");
		exampleQuizmaster.setPassword(passwordEncoder.encode("pipeweed"));
		exampleQuizmaster.setRole(QUIZMASTER);
		exampleQuizmaster.setDeleted(false);
		userRepository.saveAll(Arrays.asList(new User[] { exampleUser, exampleQuizmaster }));
		
		Round exampleRound = new Round();
		exampleRound.setTitle("Find the connection");
		exampleRound.setPrompt("See if you can identify what connects all the answers in this round. 1 point for each correct answer.");
		exampleRound.setRoundNumber(1);
		exampleRound.setDeleted(false);
		exampleRound.setAuthor(exampleQuizmaster);
		exampleRound.setCategory("Find the connection");
		exampleRound.setType("single answer");
		exampleRound.setPublished(true);
		exampleRound.setGame(exampleGame1);
		List<String> tags = new ArrayList<>();
		tags.add("connection");
		exampleRound.setTags(tags);
		exampleRound.setVisible(true);
		
		
		roundRepository.saveAll(Arrays.asList(new Round[] { exampleRound }));
		
		Question question1 = new Question();
		question1.setRound(exampleRound);
		question1.setText("This famous amphitheater is carved into a concave hillside against the backdrop of the Hollywood Hills:");
		question1.setDeleted(false);
		
		questionRepository.saveAll(Arrays.asList(new Question[] { question1 }));
//		Question question2 = new Question();
//		question2.setRound(exampleRound);
//		question2.setText("What is the name of the tomato in VeggieTales?");
//		List<String> acceptableAnswers2 = new ArrayList<>();
//		acceptableAnswers2.add("bob");
//		question2.setAcceptableAnswers(acceptableAnswers2);
//		question2.setNumberInRound(2);
//		question2.setAvailablePoints(1.0);
//		
//		Question question3 = new Question();
//		question3.setRound(exampleRound);
//		question3.setText("Name the third and final single from Kanye West’s The Life of Pablo, featuring vocals from Post Malone and Ty Dolla Sign: ");
//		List<String> acceptableAnswers3 = new ArrayList<>();
//		acceptableAnswers3.add("fade");
//		question3.setAcceptableAnswers(acceptableAnswers3);
//		question3.setNumberInRound(3);
//		question3.setAvailablePoints(1.0);
//		
//		Question question4 = new Question();
//		question4.setRound(exampleRound);
//		question4.setText("What show is being represented at this 2010 Comic-Con panel?");
//		List<String> acceptableAnswers4 = new ArrayList<>();
//		acceptableAnswers4.add("fringe");
//		question4.setAcceptableAnswers(acceptableAnswers4);
//		question4.setNumberInRound(4);
//		question4.setAvailablePoints(1.0);
//		
//		Question question5 = new Question();
//		question5.setRound(exampleRound);
//		question5.setText("This Biblical name spent over a hundred years in the top 200 most popular baby names, reaching its peak at #9 in 1996, until it fell to #227 in 2020: ");
//		List<String> acceptableAnswers5 = new ArrayList<>();
//		acceptableAnswers5.add("rachel");
//		question5.setAcceptableAnswers(acceptableAnswers5);
//		question5.setNumberInRound(5);
//		question5.setAvailablePoints(1.0);
//		
//		Question question6 = new Question();
//		question6.setRound(exampleRound);
//		question6.setText("Mark Zuckerberg participated in tennis and what other sport when he was in high school?");
//		List<String> acceptableAnswers6 = new ArrayList<>();
//		acceptableAnswers6.add("crew");
//		acceptableAnswers6.add("rowing");
//		question6.setAcceptableAnswers(acceptableAnswers6);
//		question6.setNumberInRound(6);
//		question6.setAvailablePoints(1.0);
//		
//		Question question7 = new Question();
//		question7.setRound(exampleRound);
//		question7.setText("In the earliest productions of Peter Pan, there was no mention of this magical substance; it was added into the script after several reports of children injuring themselves trying to fly off their beds: ");
//		List<String> acceptableAnswers7 = new ArrayList<>();
//		acceptableAnswers7.add("pixie");
//		acceptableAnswers7.add("pixey");
//		question7.setAcceptableAnswers(acceptableAnswers7);
//		question7.setNumberInRound(7);
//		question7.setAvailablePoints(1.0);
//		
//		Question question8 = new Question();
//		question8.setRound(exampleRound);
//		question8.setText("What do all of the above answers have in common? ");
//		List<String> acceptableAnswers8 = new ArrayList<>();
//		acceptableAnswers8.add("hair");
//		question8.setAcceptableAnswers(acceptableAnswers8);
//		question8.setNumberInRound(8);
//		question8.setAvailablePoints(1.0);
		
//		questionRepository.saveAll(Arrays.asList(new Question[] { question1, question2, question3, question4, question5, question6, question7, question8 }));
		
//		Submission exampleSubmission = new Submission();
//		exampleSubmission.setTeam(exampleTeam);
//		exampleSubmission.setRound(exampleRound);
//		exampleSubmission.setDoubleOrNothing(false);
//		exampleSubmission.setPoints(7.0);
//		List<String> answers = new ArrayList<>();
//		answers.add("hollywood bowl");
//		answers.add("Bob");
//		answers.add("Fade");
//		answers.add("Fringe");
//		answers.add("Elizabeth");
//		answers.add("rowing");
//		answers.add("pixie dust");
//		answers.add("they're all hairstyles");
//		exampleSubmission.setAnswers(answers);
//		
//		submissionRepository.saveAll(Arrays.asList(new Submission[] { exampleSubmission }));
		
		
	}

}

