package itp341.groom.bobby.finalproject.app;

import itp341.groom.bobby.finalproject.app.model.Question;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionActivity extends Activity {

	public static final String TAG = QuestionActivity.class.getSimpleName();
	public static final String EXTRA_QUESTION = "itp341.groom.bobby.finalproject.app.question";
	TextView textQuestion;
	Button buttonSubmitAnswer;
	Button buttonLeftAnswer;
	Button buttonRightAnswer;
	EditText editAnswer;
	String submittedAnswer;
	String displayQuestion;
	
	Question currentQuestion;
	
	int playersTurn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		Log.d(TAG, "onCreate()");
		
		playersTurn = 1;
		
		textQuestion = (TextView) findViewById(R.id.textQuestion);
		buttonSubmitAnswer = (Button) findViewById(R.id.buttonSubmitAnswer);
		buttonLeftAnswer = (Button) findViewById(R.id.buttonLeftAnswer);
		buttonRightAnswer = (Button) findViewById(R.id.buttonRightAnswer);
		editAnswer = (EditText) findViewById(R.id.editAnswer);
		
		//setting the visibility so some of the things are gone.
		editAnswer.setVisibility(View.GONE);
		buttonSubmitAnswer.setVisibility(View.GONE);
		
		buttonSubmitAnswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonSubmitAnswer onClick()");
				submittedAnswer = editAnswer.getText().toString();
				checkAnswer(submittedAnswer);
			}
		});
		
		buttonLeftAnswer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonLeftAnswer onClick()");
				playersTurn=1;
				answerQuestion(1);//1st player answered
			}
		});
		
		buttonRightAnswer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonRightAnswer onClick()");
				playersTurn=2;
				answerQuestion(2);//2nd player answered
			}
		});
		
		currentQuestion = (Question) getIntent().getSerializableExtra(EXTRA_QUESTION);
		displayQuestion();
	}
	
	public void checkAnswer(String guess) {
		String answer = currentQuestion.getAnswer();
		if (answer.toLowerCase().contains(guess.toLowerCase())) {
			//to help with cheating, make sure that they a least kind of answer the thing correctly
			if (Math.abs(answer.length() - guess.length())<Math.floor(answer.length()/2)) {
				textQuestion.setText("Player " + 1 + " ::guess::" + guess + ":Answer Correct: " + answer + "\n\n" + displayQuestion + "\n" + currentQuestion.getValue());
			}
			else {
				textQuestion.setText("Player " + playersTurn + ": Incorrect Guess: " + guess + "\n\n" + displayQuestion + "\n" + currentQuestion.getValue());
			}
		}
	}
	
	public void answerQuestion(int playerNum) {
		//Remove the two large overlaying buttons
		buttonLeftAnswer.setVisibility(View.INVISIBLE);
		buttonRightAnswer.setVisibility(View.INVISIBLE);
		
		//set the input area visible and accept button as well
		editAnswer.setVisibility(View.VISIBLE);
		buttonSubmitAnswer.setVisibility(View.VISIBLE);
		
		//Show which player is responsible for answering the questions.
		textQuestion.setText("Player " + playerNum + ":\n\n" + displayQuestion + "\n" + currentQuestion.getValue());
	}
	
	public void displayQuestion() {
		displayQuestion = currentQuestion.getQuestion();
		String regEx1 = "<a href=(.*?)>";
		String regEx2 = "</a>";
		String regEx3 = "<br ?/>";
		displayQuestion = displayQuestion.replaceAll(regEx1, "");
		displayQuestion = displayQuestion.replaceAll(regEx2, "");
		displayQuestion = displayQuestion.replaceAll(regEx3, "\n");
		textQuestion.setText(displayQuestion + "\n" + currentQuestion.getValue());
	}

//	@Override
//	public void onBackPressed() {
//		//super.onBackPressed();
//		return;
//	}
	
	
}
