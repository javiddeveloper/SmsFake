package com.sattar.j.smsfake;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CallenderAndTime {

    private int day;
    private int month;
    private int year;
    private int hour;
    private int min;
    private int sec;
    private long now;
    private Date date;


    public long getCalTime() {
        String timeVal = getYear() + "/" + getMonth() + "/" + getDay() + " "
                + getHour() + ":" + getMin() + ":" + getSec();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.date = null;
        try {
            date = sdf.parse(timeVal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millis = date.getTime();
        return millis;
    }


    public CallenderAndTime() {
        this.now = System.currentTimeMillis();
        String timeVal = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String[] time = timeVal.split(":");
        this.hour = Integer.parseInt(time[0]);
        this.min = Integer.parseInt(time[1]);
        this.sec = Integer.parseInt(time[2]);
        String dateVal = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        String[] date = dateVal.split("/");
        this.year = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.day = Integer.parseInt(date[2]);
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public void setDate() {

    }

    public Date getDate() {
        return date;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }
}
