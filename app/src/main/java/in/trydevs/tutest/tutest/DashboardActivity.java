package in.trydevs.tutest.tutest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import in.trydevs.tutest.tutest.DataClasses.QuestionResult;
import in.trydevs.tutest.tutest.extras.FileNames;
import in.trydevs.tutest.tutest.extras.MyApplication;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DashboardActivity extends AppCompatActivity {


    TextView takeTest;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        preferences = getSharedPreferences(FileNames.LOGIN_PREFS, MODE_PRIVATE);
        if (!preferences.getBoolean(FileNames.IS_LOGGED_IN, false)) {
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        takeTest = (TextView) findViewById(R.id.textViewTakeTest);

        takeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTask asyncTask = new MyAsyncTask();
                asyncTask.execute(preferences.getString(FileNames.UID, "0"));
            }
        });


    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {
        TextView result;

        @Override
        protected String doInBackground(String... strings) {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http") //http
                    .host("192.168.43.91")
                    .addPathSegment("myschool")
                    .addPathSegment("api")
                    .addPathSegment("v1")
                    .addPathSegment("student")
                    .addPathSegment("app")
                    .addPathSegment("getQuestions")//adds "/pathSegment" at the end of hostname
                    .addQueryParameter("uid", strings[0])
                    .build();

            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                okhttp3.Response response = client.newCall(request).execute();
                //return url.toString();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPreExecute() {
            result = (TextView) findViewById(R.id.textViewTakeTest);
        }

        @Override
        protected void onPostExecute(String s) {
            QuestionResult questionResult = MyApplication.getGson().fromJson(s, QuestionResult.class);
            //Toast.makeText(DashboardActivity.this, questionResult.toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DashboardActivity.this, TestActivity.class);
            intent.putExtra("result", questionResult);
            startActivity(intent);
        }

    }

}
