package in.trydevs.tutest.tutest.DataClasses;

/**
 * Created by kid on 06/01/17.
 */

public class TestResult {

    private String date;
    private int sno, group, marks;

    public int getGroup() {
        return group;
    }

    public int getMarks() {
        return marks;
    }

    public int getSno() {
        return sno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }
}
