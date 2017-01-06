package in.trydevs.tutest.tutest.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kid on 05/01/17.
 */

public class Question implements Parcelable {

    private int qno;
    private String question;
    private String option1, option2, option3, option4;
    private String ans;
    private int answerId;


    public String getAns() {
        return ans;
    }

    public int getQno() {
        return qno;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public int getAnswerId() {
        return answerId;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public void setQno(int qno) {
        this.qno = qno;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }


    public Question() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.qno);
        dest.writeString(this.question);
        dest.writeString(this.option1);
        dest.writeString(this.option2);
        dest.writeString(this.option3);
        dest.writeString(this.option4);
        dest.writeString(this.ans);
        dest.writeInt(this.answerId);
    }

    protected Question(Parcel in) {
        this.qno = in.readInt();
        this.question = in.readString();
        this.option1 = in.readString();
        this.option2 = in.readString();
        this.option3 = in.readString();
        this.option4 = in.readString();
        this.ans = in.readString();
        this.answerId = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
