package com.example.workoutapp.exercises;

import java.util.Calendar;

public class TdeeModel {
    int tdee;
    Calendar instance = Calendar.getInstance();
    int month;
    int week;

    public TdeeModel(int value){
        tdee = value;
        month = instance.get(Calendar.MONTH)+1;
        week = instance.get(Calendar.WEEK_OF_YEAR);
    }
    public TdeeModel(){
    }

    public int getTdee() {
        return tdee;
    }

    public void setTdee(int tdee) {
        this.tdee = tdee;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
