package com.example.workoutapp.exercises;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class ExerciseModel  {
    String name;
    Integer value;
    Calendar instance = Calendar.getInstance();
    int month;
    int week;

    public ExerciseModel(String name, Integer value){
        this.name = name;
        this.value = value;
        month = instance.get(Calendar.MONTH)+1;
        week = instance.get(Calendar.WEEK_OF_YEAR);
    }

    public ExerciseModel() {
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public int getMonth() {
        return month;
    }

    public int getWeek() {
        return week;
    }


}
