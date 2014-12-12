package itp341.groom.bobby.finalproject.app;

import itp341.groom.bobby.finalproject.app.model.Question;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends Activity {

	public static final String TAG = QuestionActivity.class.getSimpleName();
	public static final String EXTRA_QUESTION = "itp341.groom.bobby.finalproject.app.question";
	public static final String EXTRA_NUM_PLAYERS = "itp341.groom.bobby.finalproject.app.num.players";
	TextView textQuestion;
	Button buttonSubmitAnswer;
	Button buttonLeftAnswer;
	Button buttonRightAnswer;
	EditText editAnswer;
	String submittedAnswer;
	String displayQuestion;

	MediaPlayer player;

	Timer t;
	int numPlayers;

	Question currentQuestion;

	int playersTurn;
	boolean firstPlayerGuessed;
	boolean secondPlayerGuessed;
	int firstPlayerResult;
	int secondPlayerResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		Log.d(TAG, "onCreate()");

		//Initializing all of the variables
		playersTurn = 1;
		firstPlayerResult = 0;
		secondPlayerResult = 0;
		firstPlayerGuessed = false;
		secondPlayerGuessed = false;

		numPlayers = (int) getIntent().getIntExtra(EXTRA_NUM_PLAYERS, 1);

		t = new Timer();
		textQuestion = (TextView) findViewById(R.id.textQuestion);
		buttonSubmitAnswer = (Button) findViewById(R.id.buttonSubmitAnswer);
		buttonLeftAnswer = (Button) findViewById(R.id.buttonLeftAnswer);
		buttonRightAnswer = (Button) findViewById(R.id.buttonRightAnswer);
		editAnswer = (EditText) findViewById(R.id.editAnswer);

		//setting the visibility so some of the things are gone.
		editAnswer.setVisibility(View.GONE);
		buttonSubmitAnswer.setVisibility(View.GONE);
		buttonLeftAnswer.setVisibility(View.VISIBLE);
		buttonRightAnswer.setVisibility(View.VISIBLE);

		//get and display the actual Question.
		currentQuestion = (Question) getIntent().getSerializableExtra(EXTRA_QUESTION);
		displayQuestion();

		player = new MediaPlayer();

		//Set up the automatic return for no guesses
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						noAnswer();
					}
				});
			}
		}, 10000);


		//OnClickListeners for all of the buttons
		buttonSubmitAnswer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonSubmitAnswer onClick()");
				submittedAnswer = editAnswer.getText().toString();
				editAnswer.setText("");
				checkAnswer(submittedAnswer);
			}
		});

		buttonLeftAnswer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonLeftAnswer onClick()");
				t.cancel();
				t = new Timer();
				t.purge();
				try {
					AssetFileDescriptor afd = getAssets().openFd("jringin.mp3");
					player.reset();
					player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
					player.prepare();
					player.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
				playersTurn=1;
				firstPlayerGuessed = true;
				//Taking care of if there is only one person playing.
				if (numPlayers == 1) {
					playersTurn=1;
					firstPlayerGuessed = true;
					secondPlayerGuessed = true;
				}
				answerQuestion();//1st player answered
			}
		});

		buttonRightAnswer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonRightAnswer onClick()");
				t.cancel();
				t = new Timer();
				t.purge();
				try {
					AssetFileDescriptor afd = getAssets().openFd("jringin.mp3");
					player.reset();
					player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
					player.prepare();
					player.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
				playersTurn=2;
				//Taking care of if there is only one person playing.
				if (numPlayers == 1) {
					playersTurn=1;
					firstPlayerGuessed = true;
					secondPlayerGuessed = true;
				}
				secondPlayerGuessed = true;
				answerQuestion();//2nd player answered
			}
		});
	}



	//Takes as input a String that is the guess of the current player
	//If the correct answer is close to the guess, they are correct
	//A new timer is set, that once expres, returns to the previous screen.
	//points are awarded accordingly
	public void checkAnswer(String guess) {
		Log.d(TAG, "checkAnswer()");
		boolean guessAgain = false;
		String answer = currentQuestion.getAnswer();
		String regEx1 = "([a-zA-Z]+)\\\\'([a-zA-Z])";//take care of the apostrophes
		answer = answer.replaceAll(regEx1, "$1'$2");

		Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_LONG).show();
		if (answer.toLowerCase().contains(guess.toLowerCase())) {
			//to help with cheating, make sure that they a least kind of answer the thing correctly
			if (Math.abs(answer.length() - guess.length())<Math.floor(answer.length()/2)) {
				textQuestion.setText("Player " + playersTurn + ": Correct!\nAnswer: " + answer + "\n" + "+" + currentQuestion.getValue());
				calculateReturn(true);
				//hide all input methods from the player
				editAnswer.setVisibility(View.GONE);
				buttonSubmitAnswer.setVisibility(View.GONE);
				t.schedule(new TimerTask() {
					@Override
					public void run() {
						returnToGameBoard();
					}
				}, 3000);//return to the main screen after 3 seconds.
				return;
			}
			else {
				textQuestion.setText("Player " + playersTurn + ": Incorrect Guess: " + guess + "\n\n" + displayQuestion + "\n" + currentQuestion.getValue());
				calculateReturn(false);
				resetButtons();
			}
		}
		else {
			textQuestion.setText("Player " + playersTurn + ": Incorrect Guess: " + guess + "\n\n" + displayQuestion + "\n" + currentQuestion.getValue());
			calculateReturn(false);
			resetButtons();
		}
		if (firstPlayerGuessed && secondPlayerGuessed) {
			noAnswer();
		}
		else {
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							noAnswer();
						}
					});
				}
			}, 5000);//return to the main screen after 3 seconds.
		}

	}

	//Called when someone clicks on the screen
	//Enables view of the editText portion and submit button for users to answer the question.
	public void answerQuestion() {
		Log.d(TAG, "answerQuestion()");
		//Remove the two large overlaying buttons
		buttonLeftAnswer.setVisibility(View.INVISIBLE);
		buttonRightAnswer.setVisibility(View.INVISIBLE);

		//set the input area visible and accept button as well
		editAnswer.setVisibility(View.VISIBLE);
		buttonSubmitAnswer.setVisibility(View.VISIBLE);

		//Show which player is responsible for answering the questions.
		textQuestion.setText("Player " + playersTurn + ":\n\n" + displayQuestion + "\n" + currentQuestion.getValue());
	}

	//Parses the question to get rid of unwanted characters
	//Display the question on the screen
	public void displayQuestion() {
		Log.d(TAG, "displyQuestion()");
		displayQuestion = currentQuestion.getQuestion();
		String regEx1 = "<a href=(.*?)>";
		String regEx2 = "</a>";
		String regEx3 = "<br ?/>";
		String regEx4 = "([a-zA-Z]+)\\\\'([a-zA-Z])";//take care of the apostrophes
		displayQuestion = displayQuestion.replaceAll(regEx1, "");
		displayQuestion = displayQuestion.replaceAll(regEx2, "");
		displayQuestion = displayQuestion.replaceAll(regEx3, "\n");
		displayQuestion = displayQuestion.replaceAll(regEx4, "$1'$2");

		displayQuestion = displayQuestion.substring(1, displayQuestion.length()-1);//get rid of the quotes at the beginning and end of each Question.
		textQuestion.setText(displayQuestion + "\n" + currentQuestion.getValue());
	}

	//Takes as input whether or not the question was answered correctly
	//Sets the amount won or lost per player
	public void calculateReturn(boolean answeredCorrectly) {
		//parse the value string to get the actual amount for the question
		int questionAmount = Integer.parseInt(currentQuestion.getValue().substring(1, currentQuestion.getValue().length()));
		//if they answered incorrectly, then subtract the value
		questionAmount = answeredCorrectly ? questionAmount : questionAmount*-1;

		switch (playersTurn) {
		case 1:
			firstPlayerResult += questionAmount;
			break;
		case 2:
			secondPlayerResult += questionAmount;
			break;
		}
	}

	public void resetButtons() {
		Log.d(TAG, "resetButtons()");
		editAnswer.setVisibility(View.GONE);
		buttonSubmitAnswer.setVisibility(View.GONE);
		if (!firstPlayerGuessed) {
			//If the first player hasn't guessed, then make their button usable again
			buttonLeftAnswer.setVisibility(View.VISIBLE);
			buttonRightAnswer.setVisibility(View.INVISIBLE);
		}
		else if (!secondPlayerGuessed){
			//If the second player hasn't guessed, then make their button usable again
			buttonLeftAnswer.setVisibility(View.INVISIBLE);
			buttonRightAnswer.setVisibility(View.VISIBLE);
		}

	}

	public void noAnswer() {
		Log.d(TAG, "noAnswer()");
		try {
			AssetFileDescriptor afd = getAssets().openFd("jtimeUp.mp3");
			player.reset();
			player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
			player.prepare();
			player.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		t.cancel();
		t = new Timer();
		t.purge();
		String answer = currentQuestion.getAnswer();
		String regEx1 = "([a-zA-Z]+)\\\\'([a-zA-Z])";//take care of the apostrophes
		answer = answer.replaceAll(regEx1, "$1'$2");
		textQuestion.setText("Correct Answer: " + answer + "\n" + currentQuestion.getValue());
		buttonLeftAnswer.setVisibility(View.INVISIBLE);
		buttonRightAnswer.setVisibility(View.INVISIBLE);
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				returnToGameBoard();
			}
		}, 3000);
	}

	public void returnToGameBoard() {
		Intent returnResult = new Intent();
		returnResult.putExtra(GameBoardActivity.EXTRA_FIRST_PLAYER_RESULT, firstPlayerResult);
		returnResult.putExtra(GameBoardActivity.EXTRA_SECOND_PLAYER_RESULT, secondPlayerResult);
		setResult(RESULT_OK, returnResult);
		finish();
	}


	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		return;
	}


}
