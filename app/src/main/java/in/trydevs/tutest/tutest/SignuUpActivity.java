package in.trydevs.tutest.tutest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SignuUpActivity extends AppCompatActivity {

    EditText name, userName, password, email, age;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signu_up);

        name = (EditText) findViewById(R.id.editTextName);
        userName = (EditText) findViewById(R.id.editTextUserName);
        password = (EditText) findViewById(R.id.editTextPasssword);
        email = (EditText) findViewById(R.id.editTextEmail);
        age = (EditText) findViewById(R.id.editTextAge);
        submit = (Button) findViewById(R.id.buttonSignUp);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAsyncTaskRegistration().execute(email.getText().toString(), password.getText().toString(), userName.getText().toString());
            }
        });


    }

    public class MyAsyncTaskRegistration extends AsyncTask<String, Void, String> {

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
                    .addPathSegment("newRegistration")//adds "/pathSegment" at the end of hostname
                    .addQueryParameter("email", strings[0])
                    .addQueryParameter("password", strings[1])
                    .addQueryParameter("username", strings[2])
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

        }

        @Override
        protected void onPostExecute(String s) {
            //QuestionResult questionResult = MyApplication.getGson().fromJson(s, QuestionResult.class);
            Toast.makeText(SignuUpActivity.this, s, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignuUpActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }
}
