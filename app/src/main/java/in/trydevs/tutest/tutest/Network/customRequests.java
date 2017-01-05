package in.trydevs.tutest.tutest.Network;

import android.widget.Toast;

import in.trydevs.tutest.tutest.DataClasses.LoginResult;
import in.trydevs.tutest.tutest.DataClasses.Question;
import in.trydevs.tutest.tutest.extras.MyApplication;
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

    public String getQuestions(String uid){
        Call<Question> resultQuestionCall = apiInterface.getQuestions(uid);

        resultQuestionCall.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {

            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });

        while (responseData.equalsIgnoreCase(""));

        return responseData;
    }

    public LoginResult login(String email, String password){

        Call<LoginResult> loginResultCall = apiInterface.login(email, password);

        loginResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                loginResult = response.body();
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),t.toString(),Toast.LENGTH_LONG).show();
                responseData = t.toString();
            }
        });

        while (loginResult == null || responseData.equalsIgnoreCase(""));

        return loginResult;

    }

}
