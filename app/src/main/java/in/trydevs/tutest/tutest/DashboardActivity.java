package in.trydevs.tutest.tutest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.trydevs.tutest.tutest.extras.FileNames;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SharedPreferences preferences = getSharedPreferences(FileNames.LOGIN_PREFS, MODE_PRIVATE);
        if (!preferences.getBoolean(FileNames.IS_LOGGED_IN, false)){
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        
    }
}
