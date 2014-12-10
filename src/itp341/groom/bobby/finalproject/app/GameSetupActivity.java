package itp341.groom.bobby.finalproject.app;

import itp341.groom.bobby.finalproject.app.model.DataWrapper;
import itp341.groom.bobby.finalproject.app.model.Question;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.parse.CountCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class GameSetupActivity extends Activity {

	private final static String TAG = GameSetupActivity.class.getSimpleName();
	
	ArrayList<Question> gameQuestions;
	
	Button easyButton;
	Button mediumButton;
	Button hardButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "GameSetupActivity onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_setup);
		gameQuestions = new ArrayList<Question>();
		
		easyButton = (Button) findViewById(R.id.buttonEasy);
		mediumButton = (Button) findViewById(R.id.buttonMedium);
		hardButton = (Button) findViewById(R.id.buttonHard);
		
		
		//listeners
		easyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				easyStart();
			}
		});
		mediumButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mediumStart();
			}
		});
		hardButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hardStart();
			}
		});
		
	}
	
	
	
	public void easyStart() {
		ArrayList<ParseObject> questions = null;
		Log.d(TAG, "easyStart()");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
		query.whereEqualTo("difficulty", "easy");
		try {
			questions = getFullShow(query.count(), "easy");//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		} catch (ParseException e1) {
			e1.printStackTrace();
			//Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
		}
		//Toast.makeText(getApplicationContext(),  questions.get(0).getString("question"), Toast.LENGTH_SHORT).show();
		populateQuestions(questions);
	}
	
	
	
	
	public void mediumStart() {
		ArrayList<ParseObject> questions = null;
		Log.d(TAG, "mediumStart()");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
		query.whereEqualTo("difficulty", "medium");
		try {
			questions = getFullShow(query.count(), "medium");//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		} catch (ParseException e1) {
			e1.printStackTrace();
			//Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
		}
		//Toast.makeText(getApplicationContext(),  questions.get(0).getString("question"), Toast.LENGTH_SHORT).show();
		populateQuestions(questions);
	}

	public void hardStart() {
		ArrayList<ParseObject> questions = null;
		Log.d(TAG, "hardStart()");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
		query.whereEqualTo("difficulty", "hard");
		try {
			questions = getFullShow(query.count(), "hard");//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		} catch (ParseException e1) {
			e1.printStackTrace();
			//Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
		}
		//Toast.makeText(getApplicationContext(),  questions.get(0).getString("question"), Toast.LENGTH_SHORT).show();
		populateQuestions(questions);
	}
	
	
	//get a count of all of the questions
		//pick random number out of all of that count
		//get show number of that one.
			//get skip(int) question, and use the showNumber
			//query.setSkip(int)
			//querery.setLimit(1);
		//get all questions with that show number
			//if it is missing any, pick another random number, repeat
	
	public ArrayList<ParseObject> getFullShow(int numQuestions, String difficulty) {
		Log.d(TAG, "getFullShow()");
		
		int questionCount = 0;
		String showNumberString = "";
		
		while (questionCount < 30) {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
			query.whereEqualTo("difficulty", difficulty);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			showNumberString = getShowNumber(query, numQuestions);
			//get all of the jeopardies from that night
			//only the normal round, nothing extra.
			query.whereEqualTo("show_number", showNumberString);
			query.whereEqualTo("round", "Jeopardy!");
			try {
				questionCount = query.count();//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//Toast.makeText(getApplicationContext(), questionCount + " questions", Toast.LENGTH_SHORT).show();
		}
		//Toast.makeText(getApplicationContext(), questionCount + " questions Done", Toast.LENGTH_SHORT).show();
		
		ArrayList<ParseObject> fullShowList = null;
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
		query.whereEqualTo("difficulty", difficulty);
		query.whereEqualTo("show_number", showNumberString);
		query.whereEqualTo("round", "Jeopardy!");
		query.setLimit(30);
		
		try {
			fullShowList = (ArrayList<ParseObject>) query.find();//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			//Toast.makeText(getApplicationContext(), fullShowList.size() + " questions total and done", Toast.LENGTH_SHORT).show();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fullShowList;
	}
	
	
	
	public String getShowNumber(ParseQuery<ParseObject> query, int numQuestions) {
		Log.d(TAG, "getShowNumber()");
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		//get a random question out of the 
	    int questionNumber = rand.nextInt(numQuestions);
	    //get show number of that one.
	    if (questionNumber > 1000) {
	    	questionNumber %= 1000;
	    }
	    query.setSkip(questionNumber);
	    //only get the one
	    try {
	    	ParseObject result = query.getFirst();//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	    	String showNumber = result.getString("show_number");
	    	return showNumber;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}	    
	}
	
	public void populateQuestions(ArrayList<ParseObject> questions) {
//		String output = "";
		for (ParseObject po : questions) {
			
			gameQuestions.add(new Question(po.getString("question"),
					po.getString("answer"),
					po.getString("category"),
					po.getString("round"),
					po.getString("value"),
					po.getString("difficulty")));
		}
		
		Intent i = new Intent(getApplicationContext(), GameBoardActivity.class);
		i.putExtra(GameBoardActivity.EXTRA_GAME_BOARD_QUESTIONS, new DataWrapper(gameQuestions));
		startActivityForResult(i, 0);
	}
	
}



//hopefully all stored in order as of now....
//get all of the questions.
//if lacking a spot, leave it blank.
//update button text for each thing.


