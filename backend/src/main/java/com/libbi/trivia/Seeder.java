package com.libbi.trivia;
import static com.libbi.trivia.user.Role.PLAYER;
import static com.libbi.trivia.user.Role.QUIZMASTER;
import static com.libbi.trivia.question.Type.SINGLE_ANSWER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.libbi.trivia.answer.Answer;
import com.libbi.trivia.answer.AnswerRepository;
import com.libbi.trivia.game.Game;
import com.libbi.trivia.game.GameRepository;
import com.libbi.trivia.question.Question;
import com.libbi.trivia.question.QuestionRepository;
import com.libbi.trivia.round.Round;
import com.libbi.trivia.round.RoundRepository;
import com.libbi.trivia.submission.SubmissionRepository;
import com.libbi.trivia.team.Team;
import com.libbi.trivia.team.TeamRepository;
import com.libbi.trivia.user.User;
import com.libbi.trivia.user.UserRepository;

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
	private final AnswerRepository answerRepository;
	
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
		
		exampleTeam.setOwner(exampleUser);
		teamRepository.save(exampleTeam);
		
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
		exampleRound.setVisible(false);
		
		
		roundRepository.saveAll(Arrays.asList(new Round[] { exampleRound }));
		
		Question question1 = new Question();
		question1.setRound(exampleRound);
		question1.setText("This famous amphitheater is carved into a concave hillside against the backdrop of the Hollywood Hills:");
		question1.setDeleted(false);
		question1.setCorrectAnswer("The Bowl");
		question1.setType(SINGLE_ANSWER);
		
		Question question2 = new Question();
		question2.setRound(exampleRound);
		question2.setText("What is the name of the tomato in VeggieTales?");
		question2.setDeleted(false);
		question2.setCorrectAnswer("Bob");
		question2.setType(SINGLE_ANSWER);
		
		Question question3 = new Question();
		question3.setRound(exampleRound);
		question3.setText("Name the third and final single from Kanye West’s The Life of Pablo, featuring vocals from Post Malone and Ty Dolla Sign: ");
		question3.setDeleted(false);
		question3.setCorrectAnswer("Fade");
		question3.setType(SINGLE_ANSWER);
		
		Question question4 = new Question();
		question4.setRound(exampleRound);
		question4.setText("What show is being represented at this 2010 Comic-Con panel?");
		question4.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Fringe_cast_sdcc_2010_cropped.jpg/2000px-Fringe_cast_sdcc_2010_cropped.jpg");
		question4.setDeleted(false);
		question4.setCorrectAnswer("Fringe");
		question4.setType(SINGLE_ANSWER);
		
		Question question5 = new Question();
		question5.setRound(exampleRound);
		question5.setText("This Biblical name spent over a hundred years in the top 200 most popular baby names, reaching its peak at #9 in 1996, until it fell to #227 in 2020: ");
		question5.setDeleted(false);
		question5.setCorrectAnswer("Rachel");
		question5.setType(SINGLE_ANSWER);
		
		Question question6 = new Question();
		question6.setRound(exampleRound);
		question6.setText("Mark Zuckerberg participated in tennis and what other sport when he was in high school?");
		question6.setDeleted(false);
		question6.setCorrectAnswer("Crew");
		question6.setType(SINGLE_ANSWER);
		
		Question question7 = new Question();
		question7.setRound(exampleRound);
		question7.setText("In the earliest productions of Peter Pan, there was no mention of this magical substance; it was added into the script after several reports of children injuring themselves trying to fly off their beds: ");
		question7.setDeleted(false);
		question7.setCorrectAnswer("Pixie dust");
		question7.setType(SINGLE_ANSWER);
		
		Question question8 = new Question();
		question8.setRound(exampleRound);
		question8.setText("What do all of the above answers have in common? ");
		question8.setDeleted(false);
		question8.setCorrectAnswer("Hairstyles");
		question8.setType(SINGLE_ANSWER);
		
		questionRepository.saveAll(Arrays.asList(new Question[] { question1, question2, question3, question4, question5, question6, question7, question8 }));

		Answer answer1 = new Answer();
		answer1.setAvailablePoints(1.0);
		answer1.setQuestion(question1);
		List<String> correct = new ArrayList<>();
		correct.add("bowl");
		answer1.setCorrect(correct);
		answer1.setNumberOfAnswers(1);
		answer1.setDeleted(false);
		
		Answer answer2 = new Answer();
		answer2.setAvailablePoints(1.0);
		answer2.setQuestion(question2);
		List<String> correct2 = new ArrayList<>();
		correct2.add("bob");
		answer2.setCorrect(correct);
		answer2.setNumberOfAnswers(1);
		answer2.setDeleted(false);
		
		Answer answer3 = new Answer();
		answer3.setAvailablePoints(1.0);
		answer3.setQuestion(question3);
		List<String> correct3 = new ArrayList<>();
		correct3.add("fade");
		answer3.setCorrect(correct);
		answer3.setNumberOfAnswers(1);
		answer3.setDeleted(false);
		
		Answer answer4 = new Answer();
		answer4.setAvailablePoints(1.0);
		answer4.setQuestion(question4);
		List<String> correct4 = new ArrayList<>();
		correct4.add("fringe");
		answer4.setCorrect(correct);
		answer4.setNumberOfAnswers(1);
		answer4.setDeleted(false);
		
		Answer answer5 = new Answer();
		answer5.setAvailablePoints(1.0);
		answer5.setQuestion(question5);
		List<String> correct5 = new ArrayList<>();
		correct5.add("rachel");
		answer5.setCorrect(correct);
		answer5.setNumberOfAnswers(1);
		answer5.setDeleted(false);
		
		Answer answer6 = new Answer();
		answer6.setAvailablePoints(1.0);
		answer6.setQuestion(question6);
		List<String> correct6 = new ArrayList<>();
		correct6.add("crew");
		correct6.add("row");
		answer6.setCorrect(correct);
		answer6.setNumberOfAnswers(1);
		answer6.setDeleted(false);
		
		Answer answer7 = new Answer();
		answer7.setAvailablePoints(1.0);
		answer7.setQuestion(question7);
		List<String> correct7 = new ArrayList<>();
		correct7.add("pixie");
		correct7.add("pixy");
		correct7.add("dust");
		answer7.setCorrect(correct);
		answer7.setNumberOfAnswers(1);
		answer7.setDeleted(false);
		
		Answer answer8 = new Answer();
		answer8.setAvailablePoints(1.0);
		answer8.setQuestion(question8);
		List<String> correct8 = new ArrayList<>();
		correct8.add("hair");
		answer8.setCorrect(correct);
		answer8.setNumberOfAnswers(1);
		answer8.setDeleted(false);
		
		answerRepository.saveAll(Arrays.asList(new Answer[] { answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8 } ));

		question1.setAnswer(answer1);
		question2.setAnswer(answer2);
		question3.setAnswer(answer3);
		question4.setAnswer(answer4);
		question5.setAnswer(answer5);
		question6.setAnswer(answer6);
		question7.setAnswer(answer7);
		question8.setAnswer(answer8);
		
		questionRepository.saveAll(Arrays.asList(new Question[] { question1, question2, question3, question4, question5, question6, question7, question8 }));

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

