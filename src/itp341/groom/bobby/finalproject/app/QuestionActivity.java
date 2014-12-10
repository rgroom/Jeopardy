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
	EditText editAnswer;
	String submittedAnswer;
	String displayQuestion;
	
	Question currentQuestion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		Log.d(TAG, "onCreate()");
		
		textQuestion = (TextView) findViewById(R.id.textQuestion);
		buttonSubmitAnswer = (Button) findViewById(R.id.buttonSubmitAnswer);
		editAnswer = (EditText) findViewById(R.id.editAnswer);
		
		buttonSubmitAnswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onClick()");
				submittedAnswer = editAnswer.getText().toString();
				checkAnswer(submittedAnswer);
			}
		});
		
		
		currentQuestion = (Question) getIntent().getSerializableExtra(EXTRA_QUESTION);
		
		
		displayQuestion = currentQuestion.getQuestion();
		String regEx1 = "<a href=(.*?)>";
		String regEx2 = "</a>";
		String regEx3 = "<br ?/>";
		displayQuestion = displayQuestion.replaceAll(regEx1, "");
		displayQuestion = displayQuestion.replaceAll(regEx2, "");
		displayQuestion = displayQuestion.replaceAll(regEx3, "\n");
		textQuestion.setText(displayQuestion + "\n" + currentQuestion.getValue());
	}
	
	public void checkAnswer(String guess) {
		
	}

//	@Override
//	public void onBackPressed() {
//		//super.onBackPressed();
//		return;
//	}
	
	
}
