package in.trydevs.tutest.tutest.Databases;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.trydevs.tutest.tutest.DataClasses.Question;
import in.trydevs.tutest.tutest.DataClasses.TestResult;

/**
 * Created by kid on 06/01/17.
 */

public class MyDB {

    private static final int TABLE_RESULTS = 1;
    private static final int TABLE_QUESTIONS = 2;

    private SQLiteDatabase database;

    public MyDB(Context context) {
        Log.d("Database", "creating writable instance");
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        database = mySQLiteHelper.getWritableDatabase();
    }

    public void insertResult(TestResult result) {

        //create a sql prepared statement
        String sql = "INSERT INTO " + getTableName(TABLE_RESULTS) + " VALUES (?,?,?,?);";
        //compile the statement and start a transaction
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        //for a given column index, simply bind the data to be put inside that index
        statement.bindLong(2, result.getGroup());
        statement.bindLong(3, result.getMarks());
        statement.bindString(4, result.getDate());
        statement.execute();
        //set the transaction as successful and end the transaction
        database.setTransactionSuccessful();
        database.endTransaction();

    }

    public List<TestResult> getTestResults() {
        String sql = "SELECT * FROM " + getTableName(TABLE_RESULTS) + ";";
        List<TestResult> results = Collections.emptyList();

        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.isBeforeFirst()) {
                results = new ArrayList<>();
                while (cursor.moveToNext()) {
                    TestResult result = new TestResult();
                    result.setSno(cursor.getInt(0));
                    result.setGroup(cursor.getInt(1));
                    result.setMarks(cursor.getInt(2));
                    result.setDate(cursor.getString(3));
                    results.add(result);
                }
            }
            cursor.close();
        }
        return results;
    }

    public List<Question> readAllQuestions(int table) {
        List<Question> list = Collections.emptyList();
        String[] columns = {
                MySQLiteHelper.COLUMN_SNO,
                MySQLiteHelper.COLUMN_QUESTION,
                MySQLiteHelper.COLUMN_OPTION_A,
                MySQLiteHelper.COLUMN_OPTION_B,
                MySQLiteHelper.COLUMN_OPTION_C,
                MySQLiteHelper.COLUMN_OPTION_D,
                MySQLiteHelper.COLUMN_ANSWER,
        };

        Cursor cursor = database.query(getTableName(table), columns, null, null, null, null, columns[0] + " DESC ");
        if (cursor != null && cursor.moveToFirst()) {
            list = new ArrayList<>();
            int index_question = cursor.getColumnIndex(MySQLiteHelper.COLUMN_QUESTION);
            int index_optionA = cursor.getColumnIndex(MySQLiteHelper.COLUMN_OPTION_A);
            int index_optionB = cursor.getColumnIndex(MySQLiteHelper.COLUMN_OPTION_B);
            int index_optionC = cursor.getColumnIndex(MySQLiteHelper.COLUMN_OPTION_C);
            int index_optionD = cursor.getColumnIndex(MySQLiteHelper.COLUMN_OPTION_D);
            do {
                Question questions = new Question();
                questions.setQuestion(cursor.getString(index_question));
                questions.setOption1(cursor.getString(index_optionA));
                questions.setOption2(cursor.getString(index_optionB));
                questions.setOption3(cursor.getString(index_optionC));
                questions.setOption4(cursor.getString(index_optionD));
                list.add(questions);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    private String getTableName(int n) {
        switch (n) {
            case TABLE_RESULTS:
                return MySQLiteHelper.TABLE_RESULT;
            case TABLE_QUESTIONS:
                return MySQLiteHelper.TABLE_QUESTIONS;
        }
        return "";
    }

}
