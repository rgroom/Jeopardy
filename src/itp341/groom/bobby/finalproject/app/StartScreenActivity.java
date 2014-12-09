package itp341.groom.bobby.finalproject.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class StartScreenActivity extends Activity {

	public static final String TAG = StartScreenActivity.class.getSimpleName();
	
	Button startButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
	}
}
