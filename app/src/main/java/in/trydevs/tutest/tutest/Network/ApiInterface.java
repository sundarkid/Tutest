package in.trydevs.tutest.tutest.Network;

import in.trydevs.tutest.tutest.DataClasses.LoginResult;
import in.trydevs.tutest.tutest.DataClasses.QuestionResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kid on 14/11/16.
 */

public interface ApiInterface {

    @GET("getQuestions")
    Call<QuestionResult> getQuestions(@Query("uid") String UserId);

    @GET("login")
    Call<LoginResult> login(@Query("email") String email, @Query("password") String password);

}
