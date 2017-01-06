package in.trydevs.tutest.tutest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.trydevs.tutest.tutest.Adapters.AdapterQuestions;
import in.trydevs.tutest.tutest.DataClasses.QuestionResult;
import in.trydevs.tutest.tutest.extras.SpacesItemDecoration;

public class TestActivity extends AppCompatActivity {

    int SPACES_BETWEEN_ITEMS = 8;

    RecyclerView recyclerView;
    Button submit;
    AdapterQuestions adapterQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bundle bundle = getIntent().getExtras();

        QuestionResult questionResult = bundle.getParcelable("result");
        submit = (Button) findViewById(R.id.buttonSubmit);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTest);
        if (questionResult != null) {
            adapterQuestions = new AdapterQuestions(TestActivity.this, questionResult.getQuestions());
            recyclerView.setAdapter(adapterQuestions);
            recyclerView.addItemDecoration(new SpacesItemDecoration(SPACES_BETWEEN_ITEMS));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        new MyTimer().execute();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswers();
            }
        });

    }

    public void submitAnswers() {
        //Toast.makeText(TestActivity.this, MyApplication.getJson(adapterQuestions.getAnswers()), Toast.LENGTH_SHORT).show();
        QuestionResult questionResult = new QuestionResult();
        questionResult.setQuestions(adapterQuestions.getAnswers());
        Intent intent = new Intent(TestActivity.this, ResultActivity.class);
        intent.putExtra("result", questionResult);
        startActivity(intent);
        finish();
    }

    public class MyTimer extends AsyncTask<String, Integer, String> {

        TextView timer;

        @Override
        protected void onPreExecute() {
            timer = (TextView) findViewById(R.id.textViewTimer);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                for (int i = 10 * 60; i > 0; i--) {
                    Thread.sleep(1000);
                    publishProgress(Integer.valueOf(i));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int i = values[0];
            int minutes = i / 60;
            int sec = i % 60;
            timer.setText(minutes + ":" + sec);
        }

        @Override
        protected void onPostExecute(String s) {
            submitAnswers();
        }
    }

}
