package in.trydevs.tutest.tutest.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import in.trydevs.tutest.tutest.extras.MyApplication;

/**
 * Created by kid on 06/01/17.
 */

public class QuestionResult implements Parcelable {

    String result;
    List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public String getResult() {
        return result;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public QuestionResult() {
    }

    @Override
    public String toString() {
        return MyApplication.getJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.result);
        dest.writeTypedList(this.questions);
    }

    protected QuestionResult(Parcel in) {
        this.result = in.readString();
        this.questions = in.createTypedArrayList(Question.CREATOR);
    }

    public static final Creator<QuestionResult> CREATOR = new Creator<QuestionResult>() {
        @Override
        public QuestionResult createFromParcel(Parcel source) {
            return new QuestionResult(source);
        }

        @Override
        public QuestionResult[] newArray(int size) {
            return new QuestionResult[size];
        }
    };

}
