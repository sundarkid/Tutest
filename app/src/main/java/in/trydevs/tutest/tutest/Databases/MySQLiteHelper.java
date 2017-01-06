package in.trydevs.tutest.tutest.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kid on 06/01/17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static int VERSION = 1;

    public static final String TABLE_QUESTIONS = "questions";
    public static final String TABLE_RESULT = "result";
    public static final String COLUMN_SNO = "sno";
    public static final String COLUMN_GROUP = "group_id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_OPTION_A = "optionA";
    public static final String COLUMN_OPTION_B = "optionB";
    public static final String COLUMN_OPTION_C = "optionC";
    public static final String COLUMN_OPTION_D = "optionD";
    public static final String COLUMN_ANSWER = "answer";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_QUESTION_ID = "question_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MARKS = "marks";
    public static final String DB_NAME = "Tutest";


    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTIONS + " ( " +
            COLUMN_SNO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_QUESTION_ID + " INTEGER, " +
            COLUMN_USER_ID + " INTEGER, " +
            COLUMN_GROUP + " INTEGER, " +
            COLUMN_QUESTION + " TEXT, " +
            COLUMN_OPTION_A + " TEXT, " +
            COLUMN_OPTION_B + " TEXT, " +
            COLUMN_OPTION_C + " TEXT, " +
            COLUMN_OPTION_D + " TEXT, " +
            COLUMN_ANSWER + " TEXT" +
            ");";

    private static final String CREATE_TABLE_RESULTS = "CREATE TABLE IF NOT EXISTS " + TABLE_RESULT + " ( " +
            COLUMN_SNO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_GROUP + " INTEGER, " +
            COLUMN_MARKS + " INTEGER, " +
            COLUMN_DATE + " TEXT " +
            ");";
    ;

    Context context;

    public MySQLiteHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUESTIONS);
        sqLiteDatabase.execSQL(CREATE_TABLE_RESULTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
