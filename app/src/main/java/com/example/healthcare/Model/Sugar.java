package com.example.healthcare.Model;

public class Sugar {
    private int sugar_id;
    private int sugar_level;
    private String date;
    private String month_year;
    private String week;

    public Sugar(int sugar_id, int sugar_level, String date,String month_year,String week) {
        this.sugar_id = sugar_id;
        this.sugar_level = sugar_level;
        this.date = date;
        this.month_year = month_year;
        this.week = week;
    }

    public Sugar() {

    }

    public int getSugar_id() {
        return sugar_id;
    }

    public void setSugar_id(int sugar_id) {
        this.sugar_id = sugar_id;
    }

    public int getSugar_level() {
        return sugar_level;
    }

    public void setSugar_level(int sugar_level) {
        this.sugar_level = sugar_level;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
