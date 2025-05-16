package controller;

import model.Question;
import view.QuizView;

public class QuizController {
	GameController gameController;
	QuizView quizView = new QuizView(this);
	Question question;
	int indexCorrectQuestion;
	
	
	public QuizController(GameController gameController, boolean failed, int dmgAnswer, int time) {
		this.gameController = gameController;
		this.question = Question.getRandomQuestion();
		quizView.updateQuestion(question.getQuestion(), question.getShuffledAnswers());
		quizView.setLbErrorVisibility(failed);
		quizView.setHealth(gameController.getHealth());
		quizView.setDmgAnswer(dmgAnswer);
		quizView.setSeconds(time);
		quizView.timerStart();
		quizView.setVisible(true);
	}
	
	public void checkAnswer(String answer) {
		if (answer.equals(question.getAnswerCorrect())) {
			gameController.quizWin(quizView); 
		}else{
			gameController.quizFail(quizView); 
		};
	}
	
	
	
	
}
