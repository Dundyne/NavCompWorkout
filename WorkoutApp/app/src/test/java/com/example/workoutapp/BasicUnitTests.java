package com.example.workoutapp;



import com.example.workoutapp.exercises.ExerciseModel;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class BasicUnitTests {

    @Test
    public void CalcTest() {

        int parsedWeight = 50;
        int parsedHeight = 60;
        int parsedAge = 49;
        int s;
       Boolean gender = true;

        if (gender) {
            s = 5;
        }
        else {
            s = -161;
        }
        float resultsFloat = (10 * parsedWeight) + (6.25f * parsedHeight) - (5 * parsedAge) + s;

        assertEquals(resultsFloat, 635, 0);

    }

    @Test
    public void GraphDataPointTest(){

        ArrayList<Integer> progress = new ArrayList<Integer>();
        progress.add(1);
        progress.add(5);
        progress.add(60);

        DataPoint[] dataPoints = new DataPoint[progress.size()];
        int count = 0;
        for(int val : progress){
            dataPoints[count] = new DataPoint(count, val);
            count++;
        }

        int parsedInt = (int)dataPoints[0].getY();
        assertEquals(progress.get(0), parsedInt, 0);
    }

    @Test
    public void ExerciseModelTest() {
        ExerciseModel henrik = new ExerciseModel("henrik", 12);
        ExerciseModel robert = new ExerciseModel("Robert", 15);
        assertNotSame(henrik, robert);
    }
}