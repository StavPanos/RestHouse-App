package com.example.sdy51_e4;

public class Notification {
    int id;
    String title;
    String date;
    String time;
    String category;

    public Notification(){}

    public Notification(int id, String title, String date, String time, String category) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.time = time;
        this.title = title;
    }

    public Notification(String title, String date, String time, String category) {
        this.date = date;
        this.category = category;
        this.time = time;
        this.title = title;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return this.time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getCategory(){
        return this.category;
    }

    public void setCategory(String category){
        this.category = category;
    }
}
