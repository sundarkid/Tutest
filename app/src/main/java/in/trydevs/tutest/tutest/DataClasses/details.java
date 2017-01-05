package in.trydevs.tutest.tutest.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kid on 05/01/17.
 */

public class Details implements Parcelable {

    String userName;
    String uid;
    String email;

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.uid);
        dest.writeString(this.email);
    }

    public Details() {
    }

    protected Details(Parcel in) {
        this.userName = in.readString();
        this.uid = in.readString();
        this.email = in.readString();
    }

    public static final Creator<Details> CREATOR = new Creator<Details>() {
        @Override
        public Details createFromParcel(Parcel source) {
            return new Details(source);
        }

        @Override
        public Details[] newArray(int size) {
            return new Details[size];
        }
    };
}
