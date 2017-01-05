package in.trydevs.tutest.tutest.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import in.trydevs.tutest.tutest.extras.MyApplication;

/**
 * Created by kid on 05/01/17.
 */

public class LoginResult implements Parcelable {

    String result;
    List<Details> details;

    public String getResult() {
        return result;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public LoginResult() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.result);
        dest.writeTypedList(this.details);
    }

    protected LoginResult(Parcel in) {
        this.result = in.readString();
        this.details = in.createTypedArrayList(Details.CREATOR);
    }

    public static final Creator<LoginResult> CREATOR = new Creator<LoginResult>() {
        @Override
        public LoginResult createFromParcel(Parcel source) {
            return new LoginResult(source);
        }

        @Override
        public LoginResult[] newArray(int size) {
            return new LoginResult[size];
        }
    };

    @Override
    public String toString() {
        return MyApplication.getJson(this);
    }
}
