package gvprojects.simpledate;

/**
 * Created with IntelliJ IDEA.
 * User: rohrj
 * Date: 1/9/13
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDate {
              private int month, day, year;

    public SimpleDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }


}
