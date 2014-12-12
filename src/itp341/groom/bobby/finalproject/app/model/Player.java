package itp341.groom.bobby.finalproject.app.model;

import java.io.Serializable;

public class Player implements Serializable{
	
	private long _id;
	private String name;
	private int currentScore;
	private int highScore;
	
	public Player() {
		super();
	}
	
	public Player(long _id, String name, int currentScore, int highScore) {
		super();
		this._id = _id;
		this.name = name;
		this.currentScore = currentScore;
		this.highScore = highScore;
	}

	public Player(String name) {
		this.name = name;
		currentScore = 0;
		highScore = 0;
	}

	
	//Getters and Setters.
	public void set_id(long _id) {
		this._id = _id;
	}
	public long get_id() {
		return _id;
	}
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
	
	public String toString() {
		return this.name;
	}

}
