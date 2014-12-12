package itp341.groom.bobby.finalproject.app;

import itp341.groom.bobby.finalproject.app.db.DBConnector;
import itp341.groom.bobby.finalproject.app.db.DBConnector.TABLE_PLAYERS;
import itp341.groom.bobby.finalproject.app.model.Player;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class LogInActivity extends Activity {

	public final static String TAG = LogInActivity.class.getSimpleName();
	public final static String EXTRA_FIRST_PLAYER_RESULT = "itp341.groom.bobby.finalproject.app.result.first.player.result";
	public final static String EXTRA_SECOND_PLAYER_RESULT = "itp341.groom.bobby.finalproject.app.result.second.player.result";

	RadioGroup radioNumPlayers;
	RadioButton radioPlayer1;
	RadioButton radioPlayer2;

	Button buttonNewProfile;
	Button buttonPlayer1Guest;
	Button buttonPlayer2Guest;
	Button buttonStart;

	ListView listViewPlayer1;
	ListView listViewPlayer2;

	Player p1;
	Player p2;

	int numPlayers;

	Cursor c;

	DBConnector dbConnector;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");
		setContentView(R.layout.activity_log_in);

		radioNumPlayers = (RadioGroup) findViewById(R.id.radioGroupNumUsers);
		radioPlayer1 = (RadioButton) findViewById(R.id.radioUser1);
		radioPlayer2 = (RadioButton) findViewById(R.id.radioUser2);

		buttonNewProfile = (Button) findViewById(R.id.buttonNewProfile);
		buttonPlayer1Guest = (Button) findViewById(R.id.buttonPlayer1Guest);
		buttonPlayer2Guest = (Button) findViewById(R.id.buttonPlayer2Guest);
		buttonStart = (Button) findViewById(R.id.buttonStartGame);


		listViewPlayer1 = (ListView) findViewById(R.id.listViewPlayer1);
		listViewPlayer2 = (ListView) findViewById(R.id.listViewPlayer2);

		numPlayers = 1;


		//initial starting, they should not be visible.
		buttonPlayer2Guest.setVisibility(View.INVISIBLE);
		listViewPlayer2.setVisibility(View.INVISIBLE);
		radioPlayer1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buttonPlayer2Guest.setVisibility(View.INVISIBLE);
				listViewPlayer2.setVisibility(View.INVISIBLE);
				numPlayers = 1;
			}
		});
		radioPlayer2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buttonPlayer2Guest.setVisibility(View.VISIBLE);
				listViewPlayer2.setVisibility(View.VISIBLE);
				numPlayers = 2;
			}
		});

		listViewPlayer1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				loadDataPlayer1(id, p1);
			}
		});

		listViewPlayer2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				loadDataPlayer2(id, p2);
			}
		});


		//Click listeners for all of the buttons
		buttonNewProfile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onClick()");
				Intent i = new Intent(getApplicationContext(), AddPlayer.class);
				startActivityForResult(i, 1);
			}
		});

		buttonPlayer1Guest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				p1 = new Player("Guest");
				listViewPlayer1.setSelection(0);
				listViewPlayer1.clearChoices();
			}
		});

		buttonPlayer2Guest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				p2 = new Player("Guest");
				listViewPlayer2.setSelection(0);
				listViewPlayer2.clearChoices();
			}
		});

		buttonStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//starting the game
				chooseDifficulty();
			}
		});

		//setting up various other things.
		loadData();
		buttonPlayer2Guest.setVisibility(View.VISIBLE);
		listViewPlayer2.setVisibility(View.VISIBLE);
		buttonPlayer2Guest.setVisibility(View.INVISIBLE);
		listViewPlayer2.setVisibility(View.INVISIBLE);

	}

	public void loadDataPlayer1(long id, Player play) {
		Cursor c = dbConnector.selectById(id);
		if (c != null) {
			if (c.moveToFirst()) {	// otherwise something weird happened but necesssary for android
				/*editName.setText(c.getString(TABLE_COFFEE_SHOPS.COLUMN_NAME));
				editRating.setText(c.getString(TABLE_COFFEE_SHOPS.COLUMN_RATING));*/
				p1 = new Player(c.getLong(TABLE_PLAYERS.COLUMN_ID),
						c.getString(TABLE_PLAYERS.COLUMN_NAME),
						0,
						c.getInt(TABLE_PLAYERS.COLUMN_HIGH_SCORE)
						);
			}
			Toast.makeText(getApplicationContext(), "Got the Name " + p1.getName(), Toast.LENGTH_SHORT).show();
		}
	}
	
	public void loadDataPlayer2(long id, Player play) {
		Cursor c = dbConnector.selectById(id);
		if (c != null) {
			if (c.moveToFirst()) {	// otherwise something weird happened but necesssary for android
				/*editName.setText(c.getString(TABLE_COFFEE_SHOPS.COLUMN_NAME));
				editRating.setText(c.getString(TABLE_COFFEE_SHOPS.COLUMN_RATING));*/
				p2 = new Player(c.getLong(TABLE_PLAYERS.COLUMN_ID),
						c.getString(TABLE_PLAYERS.COLUMN_NAME),
						0,
						c.getInt(TABLE_PLAYERS.COLUMN_HIGH_SCORE)
						);
			}
			Toast.makeText(getApplicationContext(), "Got the Name " + p2.getName(), Toast.LENGTH_SHORT).show();
		}
	}

	public void loadData() {
		String[] from = new String[] {TABLE_PLAYERS.KEY_NAME};
		int[] to = new int[] {android.R.id.text1};

		dbConnector = new DBConnector(getApplicationContext());
		c = dbConnector.selectAll();

		Log.d(TAG, "loadData: cursor created, db closed");

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this,
				android.R.layout.simple_list_item_1,
				c,
				from,
				to,
				0);
		listViewPlayer1.setAdapter(adapter);
		listViewPlayer2.setAdapter(adapter);
		Log.d(TAG, "loadData: adapters set");
	}


	protected void onPause() {
		Log.d(TAG, "onResume()");
		super.onPause();

	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult");
		Log.d(TAG, "onActivityResult: requestCode: " + requestCode);

		if (resultCode == 2) {//result from a game that was finished
			p1.setCurrentScore(data.getIntExtra(EXTRA_FIRST_PLAYER_RESULT, 0));
			if (p1.getCurrentScore() > p1.getHighScore()) {
				p1.setHighScore(p1.getCurrentScore());
			}
			if (numPlayers==2) {
				p2.setCurrentScore(data.getIntExtra(EXTRA_SECOND_PLAYER_RESULT, 0));
				if (p2.getCurrentScore() > p2.getHighScore()) {
					p2.setHighScore(p2.getCurrentScore());
				}
			}
		}
		else if (resultCode == Activity.RESULT_OK) { // this means user pressed back

			Log.d(TAG, "onActivityResult: loading data");
			loadData();
		}


	}

	public void chooseDifficulty() {
		Intent i = new Intent(getApplicationContext(), GameSetupActivity.class);
		i.putExtra(GameSetupActivity.EXTRA_PLAYER_ONE_INFO, p1.getName());
		if (p2 != null) {
			i.putExtra(GameSetupActivity.EXTRA_PLAYER_TWO_INFO, p2.getName());
		}
		else {
			i.putExtra(GameSetupActivity.EXTRA_PLAYER_TWO_INFO, "");
		}
		i.putExtra(GameSetupActivity.EXTRA_NUM_PLAYERS, numPlayers);
		startActivityForResult(i, 2);
	}


}
