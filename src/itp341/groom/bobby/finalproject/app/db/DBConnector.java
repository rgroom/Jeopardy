package itp341.groom.bobby.finalproject.app.db;


import itp341.groom.bobby.finalproject.app.model.Player;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBConnector {
	private static final String TAG = DBConnector.class.getSimpleName();

	private SQLiteDatabase db;

	public DBConnector (Context context) {
		Log.d(TAG, "In DBConnector");
		db = new DBOpenHelper(context).getWritableDatabase();
	}


	public void close() {
		if (db != null)
			db.close();
	}

	public long insert(Player pl) {
		Log.d(TAG, "insert: " + pl.toString());

		ContentValues cv = new ContentValues();
		cv.put(TABLE_PLAYERS.KEY_NAME, pl.getName());
		cv.put(TABLE_PLAYERS.KEY_HIGH_SCORE, pl.getHighScore());

		return db.insert(TABLE_PLAYERS.NAME, null, cv);
	}

	public int deleteBySingleId(long id) {
		Log.d(TAG, "deleteSingleId: id = " + id);

		String selection = TABLE_PLAYERS.KEY_ID + "= ?";
		String[] selectionArgs = {Long.toString(id)};

		return db.delete(TABLE_PLAYERS.NAME, selection, selectionArgs);

	}

	public Cursor selectAll() {
		Log.d(TAG, "selectAll");

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
				TABLE_PLAYERS.KEY_ID,
				TABLE_PLAYERS.KEY_NAME,
				TABLE_PLAYERS.KEY_HIGH_SCORE
		};
		String sortOrder = TABLE_PLAYERS.KEY_NAME + " asc";

		//NB it is important to include KEY_ID or Creating the SimpleCursorAdapter
		//later will crash because _id not present
		Cursor cursor = db.query(TABLE_PLAYERS.NAME, 
				projection,	null, null, null, null, sortOrder);

		return cursor;
	}

	public Cursor selectById(long id) {
		Log.d(TAG, "selectById: id = " + id);

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
				TABLE_PLAYERS.KEY_ID,
				TABLE_PLAYERS.KEY_NAME,
				TABLE_PLAYERS.KEY_HIGH_SCORE
		};
		String sortOrder = TABLE_PLAYERS.KEY_NAME + " asc";
		String selection = TABLE_PLAYERS.KEY_ID + "= ?";
		String[] selectionArgs = {Long.toString(id)};

		//NB it is important to include KEY_ID or Creating the SimpleCursorAdapter
		//later will crash because _id not present
		Cursor cursor = db.query(TABLE_PLAYERS.NAME, 
				projection,	selection, selectionArgs, null, null, sortOrder);

		return cursor;
	}

	public int updateBySingleId(long id, Player pl) {
		Log.d(TAG, "UpdateSingleId: id = " + id);
		Log.d(TAG, "player info = " + pl.toString());

		ContentValues cv = new ContentValues();
		cv.put(TABLE_PLAYERS.KEY_NAME, pl.getName());
		cv.put(TABLE_PLAYERS.KEY_HIGH_SCORE, pl.getHighScore());

		String selection = TABLE_PLAYERS.KEY_ID + "= ?";
		String[] selectionArgs = {Long.toString(id)};

		return db.update(TABLE_PLAYERS.NAME, 
				cv, 
				selection, 
				selectionArgs
				);

	}


	/*
	 * Table: players
	 * =====================================================================
	 * Convenient way to keep track of all column names
	 */
	public static final class TABLE_PLAYERS {
		private static final String NAME = "players";

		// Column Names
		public static final String KEY_ID = "_id"; // follow this convention
		public static final String KEY_NAME = "name";
		public static final String KEY_HIGH_SCORE = "high_score";

		// Column indexes (good enumeration style)
		public static final int COLUMN_ID = 0;
		public static final int COLUMN_NAME = 1;
		public static final int COLUMN_HIGH_SCORE = 2;
	}

	/*
	 * Class: OpenHelper
	 * =====================================================================
	 * This serves to help open the DB and initiate the table if the DB
	 * doesn't exist
	 */
	private class DBOpenHelper extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "jeopardy_sqlite.db";
		private static final int DATABASE_VERSION = 1;

		//SQL statement to create table
		private static final String CREATE_TABLE_PLAYERS = 
				"CREATE TABLE " + TABLE_PLAYERS.NAME + " (" + 
						TABLE_PLAYERS.KEY_ID + " integer primary key autoincrement, " + 
						TABLE_PLAYERS.KEY_NAME + " TEXT, " +
						TABLE_PLAYERS.KEY_HIGH_SCORE + " INT" +
						");";

		public DBOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		//Called only first time database is created
		//Create the schema for the new table
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_PLAYERS);

		}

		//Implement this to address changes to database schema
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			Log.w("Example",
					"Upgrading database, this will drop ALL tables and recreate.");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS.NAME);


			onCreate(db);
		}

	}
}
