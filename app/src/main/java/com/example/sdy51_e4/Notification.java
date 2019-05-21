package com.example.sdy51_e4;

public class Notification {
    private int id;
    private String title;
    private String date;
    private String time;
    private String category;

    Notification(){}

    Notification(String title, String date, String time, String category) {
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

    String getTitle(){
        return this.title;
    }

    void setTitle(String title){
        this.title = title;
    }

    String getDate(){
        return this.date;
    }

    void setDate(String date){
        this.date = date;
    }

    String getTime(){
        return this.time;
    }

    void setTime(String time){
        this.time = time;
    }

    String getCategory(){
        return this.category;
    }

    void setCategory(String category){
        this.category = category;
    }
}
