package itp341.groom.bobby.finalproject.app.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DataWrapper implements Serializable{
	private ArrayList<Question> questions;
	
	public DataWrapper(ArrayList<Question> data) {
	      this.questions = data;
	   }

	   public ArrayList<Question> getQuestions() {
	      return this.questions;
	   }
	
	
}
