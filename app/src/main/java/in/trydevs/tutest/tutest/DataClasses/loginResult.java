package in.trydevs.tutest.tutest.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by kid on 05/01/17.
 */

public class LoginResult implements Parcelable {

    String result;
    List<UserDetails> details;

    public String getResult() {
        return result;
    }

    public List<UserDetails> getDetails() {
        return details;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDetails(List<UserDetails> details) {
        this.details = details;
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

    public LoginResult() {
    }

    protected LoginResult(Parcel in) {
        this.result = in.readString();
        this.details = in.createTypedArrayList(UserDetails.CREATOR);
    }

    public static final Parcelable.Creator<LoginResult> CREATOR = new Parcelable.Creator<LoginResult>() {
        @Override
        public LoginResult createFromParcel(Parcel source) {
            return new LoginResult(source);
        }

        @Override
        public LoginResult[] newArray(int size) {
            return new LoginResult[size];
        }
    };
}
