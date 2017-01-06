package in.trydevs.tutest.tutest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import in.trydevs.tutest.tutest.Adapters.AdapterQuestions;
import in.trydevs.tutest.tutest.DataClasses.QuestionResult;
import in.trydevs.tutest.tutest.extras.MyApplication;

public class TestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView timer;
    Button submit;
    AdapterQuestions adapterQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bundle bundle = getIntent().getExtras();

        QuestionResult questionResult = bundle.getParcelable("result");
        timer = (TextView) findViewById(R.id.textViewTimer);
        submit = (Button) findViewById(R.id.buttonSubmit);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTest);
        if (questionResult != null) {
            adapterQuestions = new AdapterQuestions(TestActivity.this, questionResult.getQuestions());
            recyclerView.setAdapter(adapterQuestions);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TestActivity.this, MyApplication.getJson(adapterQuestions.getAnswers()), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
