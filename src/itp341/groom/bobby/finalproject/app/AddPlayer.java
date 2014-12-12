package itp341.groom.bobby.finalproject.app;

import itp341.groom.bobby.finalproject.app.db.DBConnector;
import itp341.groom.bobby.finalproject.app.model.Player;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddPlayer extends Activity {

	private static final String TAG = AddPlayer.class.getSimpleName();
	
	Button buttonAccept;
	EditText editUsername;
	
	DBConnector dbConnector;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_player);
		
		buttonAccept = (Button) findViewById(R.id.buttonAcceptNewUser);
		editUsername = (EditText) findViewById(R.id.editUserName);
		
		buttonAccept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveAndClose();
			}
		});
		
		dbConnector = new DBConnector(getApplicationContext());
	}
	
	public void saveAndClose(){
		Log.d(TAG, "saveAndClose()");
		Player pl = new Player();
		pl.setName(editUsername.getText().toString());
		pl.setCurrentScore(0);
		pl.setHighScore(0);
		
		dbConnector.insert(pl);
		dbConnector.close();
		setResult(RESULT_OK);
		finish();
	}
}
