package com.libbi.trivia;
import static com.libbi.trivia.user.Role.*;
import static com.libbi.trivia.question.Type.*;
import static com.libbi.trivia.round.Type.*;
import static com.libbi.trivia.round.Category.*;

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
		
		Round connectionRound = new Round();
		connectionRound.setTitle("Find the connection");
		connectionRound.setPrompt("<i>See if you can identify what connects all the answers in this round. 1 point for each correct answer.</i>");
		connectionRound.setRoundNumber(1);
		connectionRound.setDeleted(false);
		connectionRound.setAuthor(exampleQuizmaster);
		connectionRound.setCategory(FIND_THE_CONNECTION);
		connectionRound.setType(QUESTION_AND_ANSWER);
		connectionRound.setPublished(true);
		connectionRound.setGame(exampleGame1);
		List<String> tags1 = new ArrayList<>();
		tags1.add("connection");
		connectionRound.setTags(tags1);
		connectionRound.setVisible(false);
		
		Round multipleAnswerRound = new Round();
		multipleAnswerRound.setTitle("Classic Board Games");
		multipleAnswerRound.setPrompt("<i>For questions with more than one answer, each correct answer is worth 1 point.</i>");
		multipleAnswerRound.setRoundNumber(2);
		multipleAnswerRound.setDeleted(false);
		multipleAnswerRound.setAuthor(exampleQuizmaster);
		multipleAnswerRound.setCategory(BOARD_GAMES);
		multipleAnswerRound.setType(QUESTION_AND_ANSWER);
		multipleAnswerRound.setPublished(true);
		multipleAnswerRound.setGame(exampleGame1);
		List<String> tags2 = new ArrayList<>();
		tags2.add("board games");
		tags2.add("retro");
		multipleAnswerRound.setTags(tags2);
		multipleAnswerRound.setVisible(false);
		
		Round matchingRound = new Round();
		matchingRound.setTitle("Jukebox Musicals");
		matchingRound.setPrompt("Match the musical artist to the title of the show based on their music:");
		matchingRound.setRoundNumber(3);
		matchingRound.setDeleted(false);
		matchingRound.setAuthor(exampleQuizmaster);
		matchingRound.setCategory(MUSIC);
		matchingRound.setType(MATCHING);
		matchingRound.setPublished(true);
		matchingRound.setGame(exampleGame1);
		List<String> tags3 = new ArrayList<>();
		tags3.add("musicals");
		tags3.add("broadway");
		matchingRound.setTags(tags3);
		matchingRound.setVisible(false);
		
		Round pictureRound = new Round();
		pictureRound.setTitle("Logos");
		pictureRound.setPrompt("Can you name the companies based on their logos?");
		pictureRound.setRoundNumber(4);
		pictureRound.setDeleted(false);
		pictureRound.setAuthor(exampleQuizmaster);
		pictureRound.setCategory(COMPANIES);
		pictureRound.setType(PICTURE);
		pictureRound.setPublished(true);
		pictureRound.setGame(exampleGame1);
		List<String> tags4 = new ArrayList<>();
		tags4.add("logos");
		pictureRound.setTags(tags4);
		pictureRound.setVisible(false);
		
		Round OneQuestionToRuleThemAllRound = new Round();
		OneQuestionToRuleThemAllRound.setTitle("Hamilton Characters");
		OneQuestionToRuleThemAllRound.setPrompt("Can you name 10 of the named characters with lines in the Broadway musical Hamilton?");
		OneQuestionToRuleThemAllRound.setRoundNumber(5);
		OneQuestionToRuleThemAllRound.setDeleted(false);
		OneQuestionToRuleThemAllRound.setAuthor(exampleQuizmaster);
		OneQuestionToRuleThemAllRound.setCategory(THEATER);
		OneQuestionToRuleThemAllRound.setType(ONE_QUESTION);
		OneQuestionToRuleThemAllRound.setPublished(true);
		OneQuestionToRuleThemAllRound.setGame(exampleGame1);
		List<String> tags5 = new ArrayList<>();
		tags5.add("hamilton");
		tags5.add("musicals");
		tags5.add("broadway");
		OneQuestionToRuleThemAllRound.setTags(tags5);
		OneQuestionToRuleThemAllRound.setVisible(false);
		
		Round guessWhoRound = new Round();
		guessWhoRound.setTitle("Name That Year");
		guessWhoRound.setPrompt("Can you name the year based on these clues? Each team only gets ONE GUESS per round. The fewer points it takes your team to get the right answer, the more points you will get.");
		guessWhoRound.setRoundNumber(6);
		guessWhoRound.setDeleted(false);
		guessWhoRound.setAuthor(exampleQuizmaster);
		guessWhoRound.setCategory(POP_CULTURE);
		guessWhoRound.setType(ONE_ANSWER);
		guessWhoRound.setPublished(true);
		guessWhoRound.setGame(exampleGame1);
		List<String> tags6 = new ArrayList<>();
		tags6.add("name the year");
		tags6.add("history");
		guessWhoRound.setTags(tags6);
		guessWhoRound.setVisible(false);
		
		
		roundRepository.saveAll(Arrays.asList(new Round[] { connectionRound, multipleAnswerRound, matchingRound, pictureRound, OneQuestionToRuleThemAllRound, guessWhoRound }));
		
		Question question1 = new Question();
		question1.setRound(connectionRound);
		question1.setText("This famous amphitheater is carved into a concave hillside against the backdrop of the Hollywood Hills:");
		question1.setDeleted(false);
		question1.setCorrectAnswer("The Bowl");
		question1.setType(SINGLE_ANSWER);
		
		Question question2 = new Question();
		question2.setRound(connectionRound);
		question2.setText("What is the name of the tomato in <i>VeggieTales</i>?");
		question2.setDeleted(false);
		question2.setCorrectAnswer("Bob");
		question2.setType(SINGLE_ANSWER);
		
		Question question3 = new Question();
		question3.setRound(connectionRound);
		question3.setText("Name the third and final single from Kanye West’s <i>The Life of Pablo<i>, featuring vocals from Post Malone and Ty Dolla Sign: ");
		question3.setDeleted(false);
		question3.setCorrectAnswer("\"Fade\"");
		question3.setType(SINGLE_ANSWER);
		
		Question question4 = new Question();
		question4.setRound(connectionRound);
		question4.setText("What show is being represented at this 2010 Comic-Con panel?");
		question4.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Fringe_cast_sdcc_2010_cropped.jpg/2000px-Fringe_cast_sdcc_2010_cropped.jpg");
		question4.setDeleted(false);
		question4.setCorrectAnswer("<i>Fringe</i>");
		question4.setType(SINGLE_ANSWER);
		
		Question question5 = new Question();
		question5.setRound(connectionRound);
		question5.setText("This Biblical name spent over a hundred years in the top 200 most popular baby names, reaching its peak at #9 in 1996, until it fell to #227 in 2020: ");
		question5.setDeleted(false);
		question5.setCorrectAnswer("Rachel");
		question5.setType(SINGLE_ANSWER);
		
		Question question6 = new Question();
		question6.setRound(connectionRound);
		question6.setText("Mark Zuckerberg participated in tennis and <b>what other sport</b> when he was in high school?");
		question6.setDeleted(false);
		question6.setCorrectAnswer("Crew");
		question6.setType(SINGLE_ANSWER);
		
		Question question7 = new Question();
		question7.setRound(connectionRound);
		question7.setText("In the earliest productions of <i>Peter Pan</i>, there was no mention of <b>this magical substance</b>; it was added into the script after several reports of children injuring themselves trying to fly off their beds: ");
		question7.setDeleted(false);
		question7.setCorrectAnswer("Pixie dust");
		question7.setType(SINGLE_ANSWER);
		
		Question question8 = new Question();
		question8.setRound(connectionRound);
		question8.setText("What do all of the above answers have in common? ");
		question8.setDeleted(false);
		question8.setCorrectAnswer("Hairstyles");
		question8.setType(SINGLE_ANSWER);
		
		Question question1bg = new Question();
		question1bg.setRound(multipleAnswerRound);
		question1bg.setText("Name the only tile in English-language <i>Scrabble</i> that is worth exactly 5 points:");
		question1bg.setDeleted(false);
		question1bg.setCorrectAnswer("K");
		question1bg.setType(SINGLE_ANSWER);
		
		Question question2bg = new Question();
		question2bg.setRound(multipleAnswerRound);
		question2bg.setText("Name the 2 player tokens in original <i>Monopoly</i> whose names start with 'T':");
		question2bg.setDeleted(false);
		question2bg.setCorrectAnswer("Thimble and Top Hat");
		question2bg.setType(MULTIPLE_ANSWERS);
		
		Question question3bg = new Question();
		question3bg.setRound(multipleAnswerRound);
		question3bg.setText("Name the 3 cards in <i>Sorry!</i> that, when drawn, allow a player to move a piece out of Start:");
		question3bg.setDeleted(false);
		question3bg.setCorrectAnswer("1, 2, and Sorry!");
		question3bg.setType(MULTIPLE_ANSWERS);
		
		Question question4bg = new Question();
		question4bg.setRound(multipleAnswerRound);
		question4bg.setText("Name the 4 colors of circles in classic <i>Twister</i>:");
		question4bg.setDeleted(false);
		question4bg.setCorrectAnswer("Red, blue, green, yellow");
		question4bg.setType(MULTIPLE_ANSWERS);
		
		Question question5bg = new Question();
		question5bg.setRound(multipleAnswerRound);
		question5bg.setText("Name the 5 pieces in classic <i>Operation</i> that are worth less than $200:");
		question5bg.setDeleted(false);
		question5bg.setCorrectAnswer("Adam's apple, wrenched ankle, water on the knee, broken heart, and spare ribs");
		question5bg.setType(MULTIPLE_ANSWERS);
		
		Question question1jm = new Question();
		question1jm.setRound(matchingRound);
		question1jm.setText("Britney Spears");
		question1jm.setDeleted(false);
		question1jm.setCorrectAnswer("Once Upon a One More Time");
		question1jm.setType(EXACT_ANSWER);
		
		Question question2jm = new Question();
		question2jm.setRound(matchingRound);
		question2jm.setText("The Who");
		question2jm.setDeleted(false);
		question2jm.setCorrectAnswer("Tommy");
		question2jm.setType(EXACT_ANSWER);
		
		Question question3jm = new Question();
		question3jm.setRound(matchingRound);
		question3jm.setText("ABBA");
		question3jm.setDeleted(false);
		question3jm.setCorrectAnswer("Mamma Mia!");
		question3jm.setType(EXACT_ANSWER);
		
		Question question4jm = new Question();
		question4jm.setRound(matchingRound);
		question4jm.setText("Alanis Morisette");
		question4jm.setDeleted(false);
		question4jm.setCorrectAnswer("Jagged Little Pill");
		question4jm.setType(EXACT_ANSWER);
		
		Question question5jm = new Question();
		question5jm.setRound(matchingRound);
		question5jm.setText("Frankie Valli and the Four Seasons");
		question5jm.setDeleted(false);
		question5jm.setCorrectAnswer("Jersey Boys");
		question5jm.setType(EXACT_ANSWER);
		
		Question question6jm = new Question();
		question6jm.setRound(matchingRound);
		question6jm.setText("Daft Punk");
		question6jm.setDeleted(false);
		question6jm.setCorrectAnswer("Interstella 5555: The 5tory of the 5ecret 5tar 5ystem");
		question6jm.setType(EXACT_ANSWER);
		
		Question question7jm = new Question();
		question7jm.setRound(matchingRound);
		question7jm.setText("Spice Girls");
		question7jm.setDeleted(false);
		question7jm.setCorrectAnswer("Viva Forever!");
		question7jm.setType(EXACT_ANSWER);
		
		Question question8jm = new Question();
		question8jm.setRound(matchingRound);
		question8jm.setText("David Bowie");
		question8jm.setDeleted(false);
		question8jm.setCorrectAnswer("Lazarus");
		question8jm.setType(EXACT_ANSWER);
		
		Question question1cl = new Question();
		question1cl.setRound(pictureRound);
		question1cl.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Faze_Clan.svg/2560px-Faze_Clan.svg.png");
		question1cl.setDeleted(false);
		question1cl.setCorrectAnswer("FaZe Clan");
		question1cl.setType(SINGLE_ANSWER);
		
		Question question2cl = new Question();
		question2cl.setRound(pictureRound);
		question2cl.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQa0mRArQ0ZhJ_2CqaP8zd9Ta0q80huS3X2gQ&s");
		question2cl.setDeleted(false);
		question2cl.setCorrectAnswer("Rolex");
		question2cl.setType(SINGLE_ANSWER);
		
		Question question3cl = new Question();
		question3cl.setRound(pictureRound);
		question3cl.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyLr9421XzikXpcGEY-12Ylt6fRN49aAqAIg&s");
		question3cl.setDeleted(false);
		question3cl.setCorrectAnswer("Nestle");
		question3cl.setType(SINGLE_ANSWER);
		
		Question question4cl = new Question();
		question4cl.setRound(pictureRound);
		question4cl.setImageUrl("https://icon2.cleanpng.com/20180425/gje/kisspng-scotch-whisky-blended-whiskey-johnnie-walker-logo-5ae0f6520f0fe1.8219693615246925620617.jpg");
		question4cl.setDeleted(false);
		question4cl.setCorrectAnswer("Johnnie Walker");
		question4cl.setType(SINGLE_ANSWER);
		
		Question question5cl = new Question();
		question5cl.setRound(pictureRound);
		question5cl.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGYRcpJD5oFFUHp5vMC1-f9tDbN_6qOL8KEg&s");
		question5cl.setDeleted(false);
		question5cl.setCorrectAnswer("23andMe");
		question5cl.setType(SINGLE_ANSWER);
		
		Question question6cl = new Question();
		question6cl.setRound(pictureRound);
		question6cl.setImageUrl("https://pbs.twimg.com/profile_images/1422562176989077506/4ojCwF5P_400x400.jpg");
		question6cl.setDeleted(false);
		question6cl.setCorrectAnswer("Avalon Hill");
		question6cl.setType(SINGLE_ANSWER);
		
		Question question7cl = new Question();
		question7cl.setRound(pictureRound);
		question7cl.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Umbrella_Corporation_logo.svg/1200px-Umbrella_Corporation_logo.svg.png");
		question7cl.setDeleted(false);
		question7cl.setCorrectAnswer("Umbrella Corporation");
		question7cl.setType(SINGLE_ANSWER);
		
		Question question8cl = new Question();
		question8cl.setRound(pictureRound);
		question8cl.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/1/1c/Screen_Gems_S_Logo.svg");
		question8cl.setDeleted(false);
		question8cl.setCorrectAnswer("Screen Gems");
		question8cl.setType(SINGLE_ANSWER);
		
		Question hamiltonQuestion = new Question();
		hamiltonQuestion.setRound(OneQuestionToRuleThemAllRound);
		hamiltonQuestion.setDeleted(false);
		hamiltonQuestion.setCorrectAnswer("Alexander Hamilton, Aaron Burr, George Washington, Marquis de Lafayette, Hercules Mulligan, John Laurens, Thomas Jefferson, James Madison, Philip Hamilton, Eliza Hamilton, Angelica Schuyler, Peggy Schuyler, Maria Reynolds, James Reynolds, King George III, Samuel Seabury, Charles Lee, George Eacker");
		
		Question question1gh = new Question();
		question1gh.setRound(guessWhoRound);
		question1gh.setText("It was the year Lorde released the song 'Royals.'");
		question1gh.setDeleted(false);
		question1gh.setCorrectAnswer("2013");
		question1gh.setType(SINGLE_ANSWER);
		
		Question question2gh = new Question();
		question2gh.setRound(guessWhoRound);
		question2gh.setText("It was the year Star Trek Into Darkness was released in theaters.");
		question2gh.setDeleted(false);
		question2gh.setCorrectAnswer("2013");
		question2gh.setType(SINGLE_ANSWER);
		
		Question question3gh = new Question();
		question3gh.setRound(guessWhoRound);
		question3gh.setText("It was the year Grand Theft Auto V was released.");
		question3gh.setDeleted(false);
		question3gh.setCorrectAnswer("2013");
		question3gh.setType(SINGLE_ANSWER);
		
		Question question4gh = new Question();
		question4gh.setRound(guessWhoRound);
		question4gh.setText("It was the year when HBO aired the Game of Thrones episode 'The Rains of Castamere.'");
		question4gh.setDeleted(false);
		question4gh.setCorrectAnswer("2013");
		question4gh.setType(SINGLE_ANSWER);
		
		Question question5gh = new Question();
		question5gh.setRound(guessWhoRound);
		question5gh.setText("It was the year the Supreme Court struck down the Defense of Marriage Act.");
		question5gh.setDeleted(false);
		question5gh.setCorrectAnswer("2013");
		question5gh.setType(SINGLE_ANSWER);
		
		questionRepository.saveAll(Arrays.asList(new Question[] { question1, question2, question3, question4, question5, question6, question7, question8, question1bg, question2bg, question3bg, question4bg, question5bg, question1jm, question2jm, question3jm, question4jm, question5jm, question6jm, question7jm, question8jm, question1cl, question2cl, question3cl, question4cl, question5cl, question6cl, question7cl, question8cl, hamiltonQuestion, question1gh, question2gh, question3gh, question4gh, question5gh }));

		Answer answer1 = new Answer();
		answer1.setAvailablePoints(1.0);
		answer1.setQuestion(question1);
		List<String> correct1 = new ArrayList<>();
		correct1.add("bowl");
		answer1.setCorrect(correct1);
		answer1.setNumberOfAnswers(1.0);
		answer1.setDeleted(false);
		
		Answer answer2 = new Answer();
		answer2.setAvailablePoints(1.0);
		answer2.setQuestion(question2);
		List<String> correct2 = new ArrayList<>();
		correct2.add("bob");
		answer2.setCorrect(correct2);
		answer2.setNumberOfAnswers(1.0);
		answer2.setDeleted(false);
		
		Answer answer3 = new Answer();
		answer3.setAvailablePoints(1.0);
		answer3.setQuestion(question3);
		List<String> correct3 = new ArrayList<>();
		correct3.add("fade");
		answer3.setCorrect(correct3);
		answer3.setNumberOfAnswers(1.0);
		answer3.setDeleted(false);
		
		Answer answer4 = new Answer();
		answer4.setAvailablePoints(1.0);
		answer4.setQuestion(question4);
		List<String> correct4 = new ArrayList<>();
		correct4.add("fringe");
		answer4.setCorrect(correct4);
		answer4.setNumberOfAnswers(1.0);
		answer4.setDeleted(false);
		
		Answer answer5 = new Answer();
		answer5.setAvailablePoints(1.0);
		answer5.setQuestion(question5);
		List<String> correct5 = new ArrayList<>();
		correct5.add("rachel");
		answer5.setCorrect(correct5);
		answer5.setNumberOfAnswers(1.0);
		answer5.setDeleted(false);
		
		Answer answer6 = new Answer();
		answer6.setAvailablePoints(1.0);
		answer6.setQuestion(question6);
		List<String> correct6 = new ArrayList<>();
		correct6.add("crew");
		correct6.add("row");
		answer6.setCorrect(correct6);
		answer6.setNumberOfAnswers(1.0);
		answer6.setDeleted(false);
		
		Answer answer7 = new Answer();
		answer7.setAvailablePoints(1.0);
		answer7.setQuestion(question7);
		List<String> correct7 = new ArrayList<>();
		correct7.add("pixie");
		correct7.add("pixy");
		correct7.add("dust");
		answer7.setCorrect(correct7);
		answer7.setNumberOfAnswers(1.0);
		answer7.setDeleted(false);
		
		Answer answer8 = new Answer();
		answer8.setAvailablePoints(1.0);
		answer8.setQuestion(question8);
		List<String> correct8 = new ArrayList<>();
		correct8.add("hair");
		answer8.setCorrect(correct8);
		answer8.setNumberOfAnswers(1.0);
		answer8.setDeleted(false);
		
		Answer answer1bg = new Answer();
		answer1bg.setAvailablePoints(1.0);
		answer1bg.setQuestion(question1bg);
		List<String> correct1bg = new ArrayList<>();
		correct1bg.add("k");
		answer1bg.setCorrect(correct1bg);
		answer1bg.setNumberOfAnswers(1.0);
		answer1bg.setDeleted(false);
		
		Answer answer2bg = new Answer();
		answer2bg.setAvailablePoints(2.0);
		answer2bg.setQuestion(question2bg);
		List<String> correct2bg = new ArrayList<>();
		correct2bg.add("thimble");
		correct2bg.add("tophat");
		correct2bg.add("top hat");
		answer2bg.setCorrect(correct2bg);
		answer2bg.setNumberOfAnswers(2.0);
		answer2bg.setDeleted(false);
		
		Answer answer3bg = new Answer();
		answer3bg.setAvailablePoints(3.0);
		answer3bg.setQuestion(question3bg);
		List<String> correct3bg = new ArrayList<>();
		correct3bg.add("1");
		correct3bg.add("one");
		correct3bg.add("2");
		correct3bg.add("two");
		correct3bg.add("sorry");
		answer3bg.setCorrect(correct3bg);
		answer3bg.setNumberOfAnswers(3.0);
		answer3bg.setDeleted(false);
		
		Answer answer4bg = new Answer();
		answer4bg.setAvailablePoints(4.0);
		answer4bg.setQuestion(question4bg);
		List<String> correct4bg = new ArrayList<>();
		correct4bg.add("green");
		correct4bg.add("blue");
		correct4bg.add("red");
		correct4bg.add("yellow");
		answer4bg.setCorrect(correct4bg);
		answer4bg.setNumberOfAnswers(4.0);
		answer4bg.setDeleted(false);
		
		Answer answer5bg = new Answer();
		answer5bg.setAvailablePoints(5.0);
		answer5bg.setQuestion(question5bg);
		List<String> correct5bg = new ArrayList<>();
		correct5bg.add("wrench");
		correct5bg.add("apple");
		correct5bg.add("water");
		correct5bg.add("pail");
		correct5bg.add("pale");
		correct5bg.add("heart");
		correct5bg.add("rib");
		answer5bg.setCorrect(correct5bg);
		answer5bg.setNumberOfAnswers(5.0);
		answer5bg.setDeleted(false);
		
		Answer answer1jm = new Answer();
		answer1jm.setAvailablePoints(1.0);
		answer1jm.setQuestion(question1jm);
		List<String> choices = new ArrayList<>(Arrays.asList("Mamma Mia!", "Once Upon a One More Time", "Rock of Ages", "Tommy", "Jagged Little Pill", "Jersey Boys", "Viva Forever!", "I Should Be So Lucky", "Lazarus", "Interstella 5555: The 5tory of the 5ecret 5tar 5ystem"));
		answer1jm.setChoices(choices);
		List<String> correct1jm = new ArrayList<>();
		correct1jm.add("Once Upon a One More Time");
		answer1jm.setCorrect(correct1jm);
		answer1jm.setNumberOfAnswers(1.0);
		answer1jm.setDeleted(false);
		
		Answer answer2jm = new Answer();
		answer2jm.setChoices(choices);
		answer2jm.setAvailablePoints(1.0);
		answer2jm.setQuestion(question2jm);
		List<String> correct2jm = new ArrayList<>();
		correct2jm.add("Tommy");
		answer2jm.setCorrect(correct2jm);
		answer2jm.setNumberOfAnswers(1.0);
		answer2jm.setDeleted(false);
		
		Answer answer3jm = new Answer();
		answer3jm.setChoices(choices);
		answer3jm.setAvailablePoints(1.0);
		answer3jm.setQuestion(question3jm);
		List<String> correct3jm = new ArrayList<>();
		correct3jm.add("Mamma Mia!");
		answer3jm.setCorrect(correct3jm);
		answer3jm.setNumberOfAnswers(1.0);
		answer3jm.setDeleted(false);
		
		Answer answer4jm = new Answer();
		answer4jm.setChoices(choices);
		answer4jm.setAvailablePoints(1.0);
		answer4jm.setQuestion(question4jm);
		List<String> correct4jm = new ArrayList<>();
		correct4jm.add("Jagged Little Pill");
		answer4jm.setCorrect(correct4jm);
		answer4jm.setNumberOfAnswers(1.0);
		answer4jm.setDeleted(false);
		
		Answer answer5jm = new Answer();
		answer5jm.setChoices(choices);
		answer5jm.setAvailablePoints(1.0);
		answer5jm.setQuestion(question5jm);
		List<String> correct5jm = new ArrayList<>();
		correct5jm.add("Jersey Boys");
		answer5jm.setCorrect(correct5jm);
		answer5jm.setNumberOfAnswers(1.0);
		answer5jm.setDeleted(false);
		
		Answer answer6jm = new Answer();
		answer6jm.setChoices(choices);
		answer6jm.setAvailablePoints(1.0);
		answer6jm.setQuestion(question6jm);
		List<String> correct6jm = new ArrayList<>();
		correct6jm.add("Interstella 5555: The 5tory of the 5ecret 5tar 5ystem");
		answer6jm.setCorrect(correct6jm);
		answer6jm.setNumberOfAnswers(1.0);
		answer6jm.setDeleted(false);
		
		Answer answer7jm = new Answer();
		answer7jm.setChoices(choices);
		answer7jm.setAvailablePoints(1.0);
		answer7jm.setQuestion(question7jm);
		List<String> correct7jm = new ArrayList<>();
		correct7jm.add("Viva Forever!");
		answer7jm.setCorrect(correct7jm);
		answer7jm.setNumberOfAnswers(1.0);
		answer7jm.setDeleted(false);
		
		Answer answer8jm = new Answer();
		answer8jm.setChoices(choices);
		answer8jm.setAvailablePoints(1.0);
		answer8jm.setQuestion(question8jm);
		List<String> correct8jm = new ArrayList<>();
		correct8jm.add("Lazarus");
		answer8jm.setCorrect(correct8jm);
		answer8jm.setNumberOfAnswers(1.0);
		answer8jm.setDeleted(false);
		
		Answer answer1cl = new Answer();
		answer1cl.setAvailablePoints(1.0);
		answer1cl.setQuestion(question1cl);
		List<String> correct1cl = new ArrayList<>();
		correct1cl.add("faze clan");
		answer1cl.setCorrect(correct1cl);
		answer1cl.setNumberOfAnswers(1.0);
		answer1cl.setDeleted(false);
		
		Answer answer2cl = new Answer();
		answer2cl.setAvailablePoints(1.0);
		answer2cl.setQuestion(question2cl);
		List<String> correct2cl = new ArrayList<>();
		correct2cl.add("rolex");
		correct2cl.add("rollex");
		answer2cl.setCorrect(correct2cl);
		answer2cl.setNumberOfAnswers(1.0);
		answer2cl.setDeleted(false);
		
		Answer answer3cl = new Answer();
		answer3cl.setAvailablePoints(1.0);
		answer3cl.setQuestion(question3cl);
		List<String> correct3cl = new ArrayList<>();
		correct3cl.add("nestle");
		correct3cl.add("nesle");
		correct3cl.add("nestlee");
		correct3cl.add("neslee");
		answer3cl.setCorrect(correct3cl);
		answer3cl.setNumberOfAnswers(1.0);
		answer3cl.setDeleted(false);
		
		Answer answer4cl = new Answer();
		answer4cl.setAvailablePoints(1.0);
		answer4cl.setQuestion(question4cl);
		List<String> correct4cl = new ArrayList<>();
		correct4cl.add("johnny");
		correct4cl.add("johnnie");
		correct4cl.add("walker");
		answer4cl.setCorrect(correct4cl);
		answer4cl.setNumberOfAnswers(1.0);
		answer4cl.setDeleted(false);
		
		Answer answer5cl = new Answer();
		answer5cl.setAvailablePoints(1.0);
		answer5cl.setQuestion(question5cl);
		List<String> correct5cl = new ArrayList<>();
		correct5cl.add("23");
		correct4cl.add("twenty");
		answer5cl.setCorrect(correct5cl);
		answer5cl.setNumberOfAnswers(1.0);
		answer5cl.setDeleted(false);
		
		Answer answer6cl = new Answer();
		answer6cl.setAvailablePoints(1.0);
		answer6cl.setQuestion(question6cl);
		List<String> correct6cl = new ArrayList<>();
		correct6cl.add("avalon hill");
		answer6cl.setCorrect(correct6cl);
		answer6cl.setNumberOfAnswers(1.0);
		answer6cl.setDeleted(false);
		
		Answer answer7cl = new Answer();
		answer7cl.setAvailablePoints(1.0);
		answer7cl.setQuestion(question7cl);
		List<String> correct7cl = new ArrayList<>();
		correct7cl.add("umbrella");
		answer7cl.setCorrect(correct7cl);
		answer7cl.setNumberOfAnswers(1.0);
		answer7cl.setDeleted(false);
		
		Answer answer8cl = new Answer();
		answer8cl.setAvailablePoints(1.0);
		answer8cl.setQuestion(question8cl);
		List<String> correct8cl = new ArrayList<>();
		correct8cl.add("screen gem");
		answer8cl.setCorrect(correct8cl);
		answer8cl.setNumberOfAnswers(1.0);
		answer8cl.setDeleted(false);
		
		Answer hamiltonAnswer = new Answer();
		hamiltonAnswer.setAvailablePoints(10.0);
		hamiltonAnswer.setQuestion(hamiltonQuestion);
		List<String> hamiltonCorrect = new ArrayList<>(Arrays.asList("hamilton", "burr", "berr", "washington", "angelica", "angelika", "eliza", "peggy", "george", "maria", "reynolds", "madison", "jefferson", "laurens", "philip", "phillip", "eacker", "eaker", "eecker", "eeker", "seabury", "seaberry", "seebury", "seeberry", "charles lee", "lafayet", "mulligan", "muligan"));
		hamiltonAnswer.setDeleted(false);
		hamiltonAnswer.setNumberOfAnswers(10.0);
		
		Answer answer1gh = new Answer();
		answer1gh.setAvailablePoints(10.0);
		answer1gh.setQuestion(question1gh);
		List<String> correctYear = new ArrayList<>();
		correctYear.add("2013");
		correctYear.add("13");
		correctYear.add("thirteen");
		answer1gh.setCorrect(correctYear);
		answer1gh.setNumberOfAnswers(1.0);
		answer1gh.setDeleted(false);
		
		Answer answer2gh = new Answer();
		answer2gh.setAvailablePoints(8.0);
		answer2gh.setQuestion(question2gh);
		answer2gh.setCorrect(correctYear);
		answer2gh.setNumberOfAnswers(1.0);
		answer2gh.setDeleted(false);
		
		Answer answer3gh = new Answer();
		answer3gh.setAvailablePoints(6.0);
		answer3gh.setQuestion(question3gh);
		answer3gh.setCorrect(correctYear);
		answer3gh.setNumberOfAnswers(1.0);
		answer3gh.setDeleted(false);
		
		Answer answer4gh = new Answer();
		answer4gh.setAvailablePoints(4.0);
		answer4gh.setQuestion(question4gh);
		answer4gh.setCorrect(correctYear);
		answer4gh.setNumberOfAnswers(1.0);
		answer4gh.setDeleted(false);
		
		Answer answer5gh = new Answer();
		answer5gh.setAvailablePoints(2.0);
		answer5gh.setQuestion(question5gh);
		answer5gh.setCorrect(correctYear);
		answer5gh.setNumberOfAnswers(1.0);
		answer5gh.setDeleted(false);
		
		answerRepository.saveAll(Arrays.asList(new Answer[] { answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer1bg, answer2bg, answer3bg, answer4bg, answer5bg, answer1jm, answer2jm, answer3jm, answer4jm, answer5jm, answer6jm, answer7jm, answer8jm, answer1cl, answer2cl, answer3cl, answer4cl, answer5cl, answer6cl, answer7cl, answer8cl, hamiltonAnswer, answer1gh, answer2gh, answer3gh, answer4gh, answer5gh } ));

		question1.setAnswer(answer1);
		question2.setAnswer(answer2);
		question3.setAnswer(answer3);
		question4.setAnswer(answer4);
		question5.setAnswer(answer5);
		question6.setAnswer(answer6);
		question7.setAnswer(answer7);
		question8.setAnswer(answer8);
		question1bg.setAnswer(answer1bg);
		question2bg.setAnswer(answer2bg);
		question3bg.setAnswer(answer3bg);
		question4bg.setAnswer(answer4bg);
		question5bg.setAnswer(answer5bg);
		question1jm.setAnswer(answer1jm);
		question2jm.setAnswer(answer2jm);
		question3jm.setAnswer(answer3jm);
		question4jm.setAnswer(answer4jm);
		question5jm.setAnswer(answer5jm);
		question6jm.setAnswer(answer6jm);
		question7jm.setAnswer(answer7jm);
		question8jm.setAnswer(answer8jm);
		question1cl.setAnswer(answer1cl);
		question2cl.setAnswer(answer2cl);
		question3cl.setAnswer(answer3cl);
		question4cl.setAnswer(answer4cl);
		question5cl.setAnswer(answer5cl);
		question6cl.setAnswer(answer6cl);
		question7cl.setAnswer(answer7cl);
		question8cl.setAnswer(answer8cl);
		hamiltonQuestion.setAnswer(hamiltonAnswer);
		question1gh.setAnswer(answer1gh);
		question2gh.setAnswer(answer2gh);
		question3gh.setAnswer(answer3gh);
		question4gh.setAnswer(answer4gh);
		question5gh.setAnswer(answer5gh);
		
		questionRepository.saveAll(Arrays.asList(new Question[] { question1, question2, question3, question4, question5, question6, question7, question8, question1bg, question2bg, question3bg, question4bg, question5bg, question1jm, question2jm, question3jm, question4jm, question5jm, question6jm, question7jm, question8jm, question1cl, question2cl, question3cl, question4cl, question5cl, question6cl, question7cl, question8cl, hamiltonQuestion, question1gh, question2gh, question3gh, question4gh, question5gh }));

//		Submission exampleSubmission = new Submission();
//		exampleSubmission.setTeam(exampleTeam);
//		exampleSubmission.setRound(connectionRound);
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

