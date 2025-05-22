package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Question {
    private int id;
    private String question;
    private String answerCorrect;
    private String answer2;
    private String answer3;
    private String answer4;

    private static ArrayList <Question> questions = new ArrayList<>();

    public Question(int id, String question, String answerCorrect, String answer2, String answer3, String answer4) {
        this.id = id;
        this.question = question;
        this.answerCorrect = answerCorrect;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        questions.add(this);
    }

    public static Question getRandomQuestion() {
    	if (questions.isEmpty()) return null;
    	Random rand = new Random();
    	int index = rand.nextInt(questions.size());
    	return questions.remove(index); 
    }
    
    
    public ArrayList <String> getShuffledAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(answerCorrect);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        
        // Metodo mezclar listas enlace de investigación:
        // https://es.stackoverflow.com/questions/144011/el-metodo-collection-shuffle-sirve-para-desordenar-solo-listas
        Collections.shuffle(answers);
        return answers;
    }

    
    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public static List<Question> getAllQuestions() {
        return questions;
    }
    

    
    
}