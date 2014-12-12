package itp341.groom.bobby.finalproject.app;

import itp341.groom.bobby.finalproject.app.model.DataWrapper;
import itp341.groom.bobby.finalproject.app.model.Question;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameBoardActivity extends Activity implements View.OnClickListener{

	public final static String TAG = GameBoardActivity.class.getSimpleName();
	//public static final String EXTRA_PIZZA_ORDER = "itp341.groom.bobby.a4.app.pizzaOrder";
	public static final String EXTRA_GAME_BOARD_QUESTIONS = "itp341.groom.bobby.finalproject.app.question";
	public static final String EXTRA_NUM_PLAYERS = "itp341.groom.bobby.finalproject.app.numplayers";
	public static final String EXTRA_FIRST_PLAYER_RESULT = "itp341.groom.bobby.finalproject.app.result.first.player";
	public static final String EXTRA_SECOND_PLAYER_RESULT = "itp341.groom.bobby.finalproject.app.result.second.player";
	public static final String EXTRA_PLAYER_ONE_NAME = "itp341.groom.bobby.finalproject.app.result.player.one.name";
	public static final String EXTRA_PLAYER_TWO_NAME = "itp341.groom.bobby.finalproject.app.result.player.two.name";

	Button buttonRow1Col1;
	Button buttonRow1Col2;
	Button buttonRow1Col3;
	Button buttonRow1Col4;
	Button buttonRow1Col5;
	Button buttonRow1Col6;
	Button buttonRow2Col1;
	Button buttonRow2Col2;
	Button buttonRow2Col3;
	Button buttonRow2Col4;
	Button buttonRow2Col5;
	Button buttonRow2Col6;
	Button buttonRow3Col1;
	Button buttonRow3Col2;
	Button buttonRow3Col3;
	Button buttonRow3Col4;
	Button buttonRow3Col5;
	Button buttonRow3Col6;
	Button buttonRow4Col1;
	Button buttonRow4Col2;
	Button buttonRow4Col3;
	Button buttonRow4Col4;
	Button buttonRow4Col5;
	Button buttonRow4Col6;
	Button buttonRow5Col1;
	Button buttonRow5Col2;
	Button buttonRow5Col3;
	Button buttonRow5Col4;
	Button buttonRow5Col5;
	Button buttonRow5Col6;

	TextView textCol1;
	TextView textCol2;
	TextView textCol3;
	TextView textCol4;
	TextView textCol5;
	TextView textCol6;

	TextView textPlayer1;
	TextView textPlayer1Score;
	TextView textWhosTurn;
	TextView textPlayer2;
	TextView textPlayer2Score;


	DataWrapper dw;
	ArrayList<Question> questions;
	int numPlayers;

	int firstPlayerScore;
	int secondPlayerScore;
	int questionsAnswered;
	
	String player1name;
	String player2name;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "GameBoardActivity onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_board);

		buttonRow1Col1 = (Button) findViewById(R.id.buttonRow1Col1);
		buttonRow1Col2 = (Button) findViewById(R.id.buttonRow1Col2);
		buttonRow1Col3 = (Button) findViewById(R.id.buttonRow1Col3);
		buttonRow1Col4 = (Button) findViewById(R.id.buttonRow1Col4);
		buttonRow1Col5 = (Button) findViewById(R.id.buttonRow1Col5);
		buttonRow1Col6 = (Button) findViewById(R.id.buttonRow1Col6);
		buttonRow2Col1 = (Button) findViewById(R.id.buttonRow2Col1);
		buttonRow2Col2 = (Button) findViewById(R.id.buttonRow2Col2);
		buttonRow2Col3 = (Button) findViewById(R.id.buttonRow2Col3);
		buttonRow2Col4 = (Button) findViewById(R.id.buttonRow2Col4);
		buttonRow2Col5 = (Button) findViewById(R.id.buttonRow2Col5);
		buttonRow2Col6 = (Button) findViewById(R.id.buttonRow2Col6);
		buttonRow3Col1 = (Button) findViewById(R.id.buttonRow3Col1);
		buttonRow3Col2 = (Button) findViewById(R.id.buttonRow3Col2);
		buttonRow3Col3 = (Button) findViewById(R.id.buttonRow3Col3);
		buttonRow3Col4 = (Button) findViewById(R.id.buttonRow3Col4);
		buttonRow3Col5 = (Button) findViewById(R.id.buttonRow3Col5);
		buttonRow3Col6 = (Button) findViewById(R.id.buttonRow3Col6);
		buttonRow4Col1 = (Button) findViewById(R.id.buttonRow4Col1);
		buttonRow4Col2 = (Button) findViewById(R.id.buttonRow4Col2);
		buttonRow4Col3 = (Button) findViewById(R.id.buttonRow4Col3);
		buttonRow4Col4 = (Button) findViewById(R.id.buttonRow4Col4);
		buttonRow4Col5 = (Button) findViewById(R.id.buttonRow4Col5);
		buttonRow4Col6 = (Button) findViewById(R.id.buttonRow4Col6);
		buttonRow5Col1 = (Button) findViewById(R.id.buttonRow5Col1);
		buttonRow5Col2 = (Button) findViewById(R.id.buttonRow5Col2);
		buttonRow5Col3 = (Button) findViewById(R.id.buttonRow5Col3);
		buttonRow5Col4 = (Button) findViewById(R.id.buttonRow5Col4);
		buttonRow5Col5 = (Button) findViewById(R.id.buttonRow5Col5);
		buttonRow5Col6 = (Button) findViewById(R.id.buttonRow5Col6);

		textCol1 = (TextView) findViewById(R.id.textCol1);
		textCol2 = (TextView) findViewById(R.id.textCol2);
		textCol3 = (TextView) findViewById(R.id.textCol3);
		textCol4 = (TextView) findViewById(R.id.textCol4);
		textCol5 = (TextView) findViewById(R.id.textCol5);
		textCol6 = (TextView) findViewById(R.id.textCol6);

		textPlayer1 = (TextView) findViewById(R.id.textPlayer1);
		textPlayer1Score = (TextView) findViewById(R.id.textPlayer1Score);
		textWhosTurn = (TextView) findViewById(R.id.textWhosTurn);
		textPlayer2 = (TextView) findViewById(R.id.textPlayer2);
		textPlayer2Score = (TextView) findViewById(R.id.textPlayer2Score);

		buttonRow1Col1.setOnClickListener(this);
		buttonRow1Col2.setOnClickListener(this);
		buttonRow1Col3.setOnClickListener(this);
		buttonRow1Col4.setOnClickListener(this);
		buttonRow1Col5.setOnClickListener(this);
		buttonRow1Col6.setOnClickListener(this);
		buttonRow2Col1.setOnClickListener(this);
		buttonRow2Col2.setOnClickListener(this);
		buttonRow2Col3.setOnClickListener(this);
		buttonRow2Col4.setOnClickListener(this);
		buttonRow2Col5.setOnClickListener(this);
		buttonRow2Col6.setOnClickListener(this);
		buttonRow3Col1.setOnClickListener(this);
		buttonRow3Col2.setOnClickListener(this);
		buttonRow3Col3.setOnClickListener(this);
		buttonRow3Col4.setOnClickListener(this);
		buttonRow3Col5.setOnClickListener(this);
		buttonRow3Col6.setOnClickListener(this);
		buttonRow4Col1.setOnClickListener(this);
		buttonRow4Col2.setOnClickListener(this);
		buttonRow4Col3.setOnClickListener(this);
		buttonRow4Col4.setOnClickListener(this);
		buttonRow4Col5.setOnClickListener(this);
		buttonRow4Col6.setOnClickListener(this);
		buttonRow5Col1.setOnClickListener(this);
		buttonRow5Col2.setOnClickListener(this);
		buttonRow5Col3.setOnClickListener(this);
		buttonRow5Col4.setOnClickListener(this);
		buttonRow5Col5.setOnClickListener(this);
		buttonRow5Col6.setOnClickListener(this);


		dw = (DataWrapper) getIntent().getSerializableExtra(EXTRA_GAME_BOARD_QUESTIONS);
		questions = dw.getQuestions();
		numPlayers = (int) getIntent().getIntExtra(EXTRA_NUM_PLAYERS, 1);
		player1name = getIntent().getStringExtra(EXTRA_PLAYER_ONE_NAME);
		player2name = getIntent().getStringExtra(EXTRA_PLAYER_TWO_NAME);
		
		textPlayer1Score.setText(" $" + 0);
		textPlayer2Score.setText(" $" + 0);
		
		if (numPlayers == 1) {
			textPlayer2.setVisibility(View.INVISIBLE);
			textPlayer2Score.setVisibility(View.INVISIBLE);
		}

		textPlayer1.setText(player1name + ": ");
		textPlayer2.setText(player2name + ": ");
		
		textCol1.setText(questions.get(0).getCategory());
		textCol2.setText(questions.get(1).getCategory());
		textCol3.setText(questions.get(2).getCategory());
		textCol4.setText(questions.get(3).getCategory());
		textCol5.setText(questions.get(4).getCategory());
		textCol6.setText(questions.get(5).getCategory());
		/*DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("data");
		ArrayList<Parliament> list = dw.getParliaments();*/

		firstPlayerScore = 0;
		secondPlayerScore = 0;
		questionsAnswered = 0;
	}


	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick()");
		String buttonId = v.getResources().getResourceName(v.getId());
		String bleh = buttonId;
		bleh = bleh.replace("itp341.groom.bobby.finalproject.app:id/buttonRow", "");
		String[] rowCol = bleh.split("Col");
		String blah = v.getResources().getResourceName(v.getId());
		//Toast.makeText(getApplicationContext(), rowCol[0]+"x"+rowCol[1], Toast.LENGTH_SHORT).show();

		//disable the button.
		Button tileClicked = (Button) findViewById(v.getId());
		tileClicked.setEnabled(false);
		tileClicked.setText("");

		int row = Integer.parseInt(rowCol[0]);
		int col = Integer.parseInt(rowCol[1]);
		int index = (row-1)*6 + col-1;
		//Toast.makeText(getApplicationContext(), questions.get(index).getQuestion() + "\n" + questions.get(index).getAnswer(), Toast.LENGTH_LONG).show();


		Intent i = new Intent(getApplicationContext(), QuestionActivity.class);
		i.putExtra(QuestionActivity.EXTRA_QUESTION, questions.get(index));
		i.putExtra(QuestionActivity.EXTRA_NUM_PLAYERS, numPlayers);
		startActivityForResult(i, 0);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult()");
		questionsAnswered++;

		if (data!=null) {
			firstPlayerScore += data.getIntExtra(EXTRA_FIRST_PLAYER_RESULT, 0);
			secondPlayerScore += data.getIntExtra(EXTRA_SECOND_PLAYER_RESULT, 0);
			if (numPlayers==2) {
				if (data.getIntExtra(EXTRA_FIRST_PLAYER_RESULT, 0) > data.getIntExtra(EXTRA_SECOND_PLAYER_RESULT, 0)) {
					textWhosTurn.setText("Player 1, please choose a category");
				}
				else {
					textWhosTurn.setText("Player 2, please choose a category");
				}
			}
		}
		//Toast.makeText(getApplicationContext(), data.getIntExtra(EXTRA_FIRST_PLAYER_RESULT, 0) + "\n" + data.getIntExtra(EXTRA_SECOND_PLAYER_RESULT, 0), Toast.LENGTH_LONG).show();

		textPlayer1Score.setText(" $" + firstPlayerScore);
		textPlayer2Score.setText(" $" + secondPlayerScore);

		if (questionsAnswered == 30) {
			/*Intent returnResult = new Intent();
		returnResult.putExtra(GameBoardActivity.EXTRA_FIRST_PLAYER_RESULT, firstPlayerResult);
		returnResult.putExtra(GameBoardActivity.EXTRA_SECOND_PLAYER_RESULT, secondPlayerResult);
		setResult(RESULT_OK, returnResult);*/
			Intent returnResult = new Intent();
			returnResult.putExtra(GameSetupActivity.EXTRA_FIRST_PLAYER_RESULT, firstPlayerScore);
			returnResult.putExtra(GameSetupActivity.EXTRA_SECOND_PLAYER_RESULT, secondPlayerScore);
			finish();
		}
	}


	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		return;
	}
}
