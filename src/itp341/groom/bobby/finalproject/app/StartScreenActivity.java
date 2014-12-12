package itp341.groom.bobby.finalproject.app;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartScreenActivity extends Activity {

	public static final String TAG = StartScreenActivity.class.getSimpleName();
	
	Button startButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "StartScreenActivity onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		
		startButton = (Button) findViewById(R.id.buttonStartScreen);
		
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "startButton onClick()");
				startGame();
			}
		});
		
		
		Parse.initialize(this, "CyO2nm8v8Ogr1OrGiDtsqSKZQjSUe2oA5KOyoRWX", "ZUtSPRwqXCd4TJjUQXStwWX5yfs8CFrxeHn2t5aH");
		
		
		//got parse working
//		ParseObject testObject = new ParseObject("TestObject");
//		testObject.put("foo", "bar");
//		testObject.saveInBackground();
	}
	
	
	private void startGame() {
		Log.d(TAG, "startGame()");
		Intent i = new Intent(getApplicationContext(), LogInActivity.class);
		startActivityForResult(i, 0);
	}
}
