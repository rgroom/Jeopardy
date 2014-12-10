package itp341.groom.bobby.finalproject.app.model;

import java.io.Serializable;

public class Question implements Serializable{
	private String question;
	private String answer;
	private String category;
	private String round;
	private String value;
	//possibly make an enum?
	private String difficulty;
	
	//Constructors
	public Question(String question, String answer, String category,
			String round, String value, String difficulty) {
		super();
		this.question = question;
		this.answer = answer;
		this.category = category;
		this.round = round;
		this.value = value;
		this.difficulty = difficulty;
	}

	
	
	
	//Getters and Setters
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
}
