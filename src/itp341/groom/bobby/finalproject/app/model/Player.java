package itp341.groom.bobby.finalproject.app.model;

import java.io.Serializable;

public class Player implements Serializable{
	
	private String name;
	private int currentScore;
	private int highScore;
	private String highScoreGameBoard;
	
	public Player(String name) {
		this.name = name;
	}

	
	//Getters and Setters.
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public String getHighScoreGameBoard() {
		return highScoreGameBoard;
	}

	public void setHighScoreGameBoard(String highScoreGameBoard) {
		this.highScoreGameBoard = highScoreGameBoard;
	}
	
	

}
