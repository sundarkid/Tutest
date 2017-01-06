package in.trydevs.tutest.tutest.extras;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import in.trydevs.tutest.tutest.Databases.MyDB;

/**
 * Created by kid on 05/01/17.
 */

public class MyApplication extends Application {

    private static MyApplication myInstance;
    private static MyDB dataBase;
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

    public synchronized static MyDB getWritableDatabase(){
        if (dataBase == null) {
            Log.d("database", "creating object");
            dataBase = new MyDB(getContext());
        }
        Log.d("Database", "return");
        return dataBase;
    }

    public static Gson getGson() {
        return gson;
    }
}
