package com.example.healthcare.Model;

public class BPLevel {

    private int pb_id;
    private int systolic;
    private int diastolic;
    private String month_year;
    private String week;
    private String date;

    public BPLevel(int pb_id, int systolic, int diastolic, String month_year, String week, String date) {
        this.pb_id = pb_id;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.month_year = month_year;
        this.week = week;
        this.date = date;
    }

    public BPLevel() {

    }

    public int getPb_id() {
        return pb_id;
    }

    public void setPb_id(int pb_id) {
        this.pb_id = pb_id;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public String getMonth_year() {
        return month_year;
    }

    public void setMonth_year(String month_year) {
        this.month_year = month_year;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
