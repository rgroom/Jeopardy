package itp341.groom.bobby.finalproject.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class GameBoardActivity extends Activity {

	public final static String TAG = GameBoardActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "GameBoardActivity onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_board);
	}
}
