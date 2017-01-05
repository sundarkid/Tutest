package in.trydevs.tutest.tutest.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kid on 05/01/17.
 */

public class UserDetails implements Parcelable {

    String userName;
    String uid;
    String email;

    public String getUserName() {
        return userName;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public UserDetails() {
    }

    protected UserDetails(Parcel in) {
        this.userName = in.readString();
        this.uid = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<UserDetails> CREATOR = new Parcelable.Creator<UserDetails>() {
        @Override
        public UserDetails createFromParcel(Parcel source) {
            return new UserDetails(source);
        }

        @Override
        public UserDetails[] newArray(int size) {
            return new UserDetails[size];
        }
    };
}
