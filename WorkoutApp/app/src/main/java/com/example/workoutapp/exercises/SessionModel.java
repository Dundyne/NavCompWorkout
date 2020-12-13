package com.example.workoutapp.exercises;

import java.util.ArrayList;

public class SessionModel {
    ArrayList<ExerciseModel> exercises = new ArrayList<>();

    public SessionModel() {

    }

    public ArrayList<ExerciseModel> getExercises() {
        return exercises;
    }

    public void setExercises(ExerciseModel exercise) {
        this.exercises.add(exercise);
    }
}
