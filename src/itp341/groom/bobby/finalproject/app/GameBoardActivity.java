package itp341.groom.bobby.finalproject.app;

import java.util.ArrayList;

import itp341.groom.bobby.finalproject.app.model.DataWrapper;
import itp341.groom.bobby.finalproject.app.model.Question;
import android.app.Activity;
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
	
	
	DataWrapper dw;
	ArrayList<Question> questions;
	
	
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
		
		textCol1.setText(questions.get(0).getCategory());
		textCol2.setText(questions.get(1).getCategory());
		textCol3.setText(questions.get(2).getCategory());
		textCol4.setText(questions.get(3).getCategory());
		textCol5.setText(questions.get(4).getCategory());
		textCol6.setText(questions.get(5).getCategory());
		/*DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("data");
		ArrayList<Parliament> list = dw.getParliaments();*/
	}
	
	
	@Override
	public void onClick(View v) {
		String bleh = v.getResources().getResourceName(v.getId());
		bleh = bleh.replace("itp341.groom.bobby.finalproject.app:id/buttonRow", "");
		String[] rowCol = bleh.split("Col");
		String blah = v.getResources().getResourceName(v.getId());
		Toast.makeText(getApplicationContext(), rowCol[0]+"x"+rowCol[1], Toast.LENGTH_SHORT).show();
		
		//(row-1)*6 + col
		int row = Integer.parseInt(rowCol[0]);
		int col = Integer.parseInt(rowCol[1]);
		int index = (row-1)*6 + col;
		//questions.get(index).getQuestion() + "\n" + questions.get(index).getAnswer()
		Toast.makeText(getApplicationContext(), questions.get(index).getQuestion() + "\n" + questions.get(index).getAnswer(), Toast.LENGTH_LONG).show();
		
		
//		switch (v.getId()) {
//		case R.id.buttonRow1Col1:
////			String bleh = v.getResources().getResourceName(v.getId());
////			Toast.makeText(getApplicationContext(), bleh, Toast.LENGTH_SHORT).show();
//			break;
//
//		default:
//			break;
//		}
	}
}
