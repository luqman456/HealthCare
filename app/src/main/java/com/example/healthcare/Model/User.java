package com.example.healthcare.Model;

public class User {

    private int id;
    private String name;
    private String email;
    private int age;
    private String height;
    private String weight;
    private String password;
    private String confirmpassword;
    private int sugar_level;
    private int bp_level;
    private String sugar_level_date;
    private String bp_level_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public int getSugar_level() {
        return sugar_level;
    }

    public void setSugar_level(int sugar_level) {
        this.sugar_level = sugar_level;
    }

    public int getBp_level() {
        return bp_level;
    }

    public void setBp_level(int bp_level) {
        this.bp_level = bp_level;
    }

    public String getSugar_level_date() {
        return sugar_level_date;
    }

    public void setSugar_level_date(String sugar_level_date) {
        this.sugar_level_date = sugar_level_date;
    }

    public String getBp_level_date() {
        return bp_level_date;
    }

    public void setBp_level_date(String bp_level_date) {
        this.bp_level_date = bp_level_date;
    }
}
