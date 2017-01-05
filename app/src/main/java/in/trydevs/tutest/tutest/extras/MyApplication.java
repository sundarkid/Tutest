package in.trydevs.tutest.tutest.extras;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by kid on 05/01/17.
 */

public class MyApplication extends Application {

    private static MyApplication myInstance;
    private static Gson gson = new Gson();

    public static Application getInstance(){
        return myInstance;
    }

    public static Context getContext(){
        return myInstance.getApplicationContext();
    }

    public static String getJson(Object object){
        return gson.toJson(object);
    }

}
