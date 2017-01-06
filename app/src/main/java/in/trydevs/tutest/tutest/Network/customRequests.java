package in.trydevs.tutest.tutest.Network;

import android.widget.Toast;

import java.io.IOException;

import in.trydevs.tutest.tutest.DataClasses.LoginResult;
import in.trydevs.tutest.tutest.DataClasses.QuestionResult;
import in.trydevs.tutest.tutest.extras.MyApplication;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kid on 18/11/16.
 */

public class CustomRequests {

    // Getting api for service
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    String responseData = "";
    LoginResult loginResult;

    public String getQuestions(String uid) {
        Call<QuestionResult> resultQuestionCall = apiInterface.getQuestions(uid);

        resultQuestionCall.enqueue(new Callback<QuestionResult>() {
            @Override
            public void onResponse(Call<QuestionResult> call, Response<QuestionResult> response) {
                Toast.makeText(MyApplication.getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<QuestionResult> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        while (responseData.equalsIgnoreCase("")) ;

        return responseData;
    }

    public LoginResult login(String email, String password) {

        Call<LoginResult> loginResultCall = apiInterface.login(email, password);

        loginResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                loginResult = response.body();
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(), t.toString(), Toast.LENGTH_LONG).show();
                responseData = t.toString();
            }
        });

        while (loginResult == null || responseData.equalsIgnoreCase("")) ;

        return loginResult;

    }

    public static String GET(OkHttpClient client, HttpUrl url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
