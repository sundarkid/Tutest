package in.trydevs.tutest.tutest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;

import in.trydevs.tutest.tutest.DataClasses.LoginResult;
import in.trydevs.tutest.tutest.Network.ApiClient;
import in.trydevs.tutest.tutest.Network.ApiInterface;
import in.trydevs.tutest.tutest.extras.FileNames;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String responseData;
    EditText email, password;
    ActionProcessButton login;
    LoginResult loginResult;
    TextView signUp;

    // Getting api for service
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseData = "";
        SharedPreferences preferences = getSharedPreferences(FileNames.LOGIN_PREFS, MODE_PRIVATE);
        if (preferences.getBoolean(FileNames.IS_LOGGED_IN, false)) {
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        // Mapping the editText objects
        email = (EditText) findViewById(R.id.loginMail);
        password = (EditText) findViewById(R.id.loginPass);
        login = (ActionProcessButton) findViewById(R.id.loginButton);
        signUp = (TextView) findViewById(R.id.login_signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignuUpActivity.class);
                startActivity(intent);
            }
        });

        // Setting the configuration for the button
        login.setMode(ActionProcessButton.Mode.ENDLESS);

        // Function to perform network task on click og the Login Button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Progress Dialog for login
                login.setProgress(1);

                // Trying to connect to network and login
                Call<LoginResult> loginResultCall = apiInterface.login(email.getText().toString(), password.getText().toString());

                loginResultCall.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        loginResult = response.body();
                        // Saving the details on successful Login
                        SharedPreferences preferences = getSharedPreferences(FileNames.LOGIN_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        if (loginResult.getResult().equalsIgnoreCase("success")) {
                            editor.putBoolean(FileNames.IS_LOGGED_IN, true);
                            editor.putString(FileNames.EMAIL, loginResult.getDetails().get(0).getEmail());
                            editor.putString(FileNames.USER_NAME, loginResult.getDetails().get(0).getUserName());
                            editor.putString(FileNames.UID, loginResult.getDetails().get(0).getUid());
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // On failed login alerting the user
                            email.setText("");
                            password.setText("");
                            Toast.makeText(MainActivity.this, "Wrong details please try again.", Toast.LENGTH_SHORT).show();
                            login.setProgress(0);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        // Case-might not be connected to network so alerting the user
                        email.setText("");
                        password.setText("");
                        Toast.makeText(MainActivity.this, "Check whether Connected to Network.", Toast.LENGTH_SHORT).show();
                        login.setProgress(0);
                        responseData = t.toString();
                    }
                });


            }
        });


    }
}
