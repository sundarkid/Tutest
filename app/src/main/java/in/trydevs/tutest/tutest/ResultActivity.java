package in.trydevs.tutest.tutest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import in.trydevs.tutest.tutest.DataClasses.QuestionResult;

public class ResultActivity extends AppCompatActivity {

    QuestionResult questionResult;
    int marks;
    TextView result;
    ListView correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();

        questionResult = bundle.getParcelable("result");
        marks = 0;
        result = (TextView) findViewById(R.id.textViewMarks);
        correctAnswers = (ListView) findViewById(R.id.listViewResult);

        if (questionResult != null) {
            for (int i = 0; i < questionResult.getQuestions().size(); i++)
                if (questionResult.getQuestions().get(i).getAns().equalsIgnoreCase(questionResult.getQuestions().get(i).getSelectedAnswer()))
                    marks++;
        }

        String[] answers = new String[questionResult.getQuestions().size()];

        for (int i = 0; i < questionResult.getQuestions().size(); i++) {
            answers[i] = questionResult.getQuestions().get(i).getQuestion() + "\n Correct Answer: " + questionResult.getQuestions().get(i).getAns();
        }

        //Toast.makeText(ResultActivity.this, MyApplication.getJson(answers), Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ResultActivity.this, R.layout.activity_listview, answers);
        correctAnswers.setAdapter(adapter);
        result.setText(marks + "/" + questionResult.getQuestions().size());
    }
}
