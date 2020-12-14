package com.example.workoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.workoutapp.databinding.ActivityGraphBinding;
import com.example.workoutapp.exercises.ExerciseModel;
import com.example.workoutapp.exercises.SessionModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GraphActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityGraphBinding binding;
    String exercise = "hollow";
    private FirebaseFirestore firestoreDb;
    private CollectionReference exercisesCollection;
    LineGraphSeries<DataPoint> lineSeriesTest;
    private List<ExerciseModel> exerciseList;
    GraphView linegraph;
    Spinner spinner;

    public GraphActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGraphBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firestoreDb = FirebaseFirestore.getInstance();

        exercisesCollection = firestoreDb.collection(exercise);
        spinner = binding.spinner;
        linegraph = binding.lineGraph;
        exerciseList = new ArrayList<>();
        binding.graphTextView.setText("Progress last 2 weeks. \n Minutes or Repetitions");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        createFirestoreReadListener();
        //createGraph();

    }

    private ArrayList<Integer> getExerciseValueTwoWeeks(){
        //Planla å legge til knapp som endrer hvor mange uker du viser men rakk ikke.
        Integer[] intArray;
        Calendar instance = Calendar.getInstance();
        int week = instance.get(Calendar.WEEK_OF_YEAR);

        ArrayList<Integer> returnValues = new ArrayList<Integer>();
        returnValues.add(0);
        for (ExerciseModel exercise: exerciseList
             ) {
            if(exercise.getWeek() > week-2)
            returnValues.add(exercise.getValue());
        }

        return returnValues;

    }


    private void createFirestoreReadListener() {

        exercisesCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    exerciseList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        //DocumentReference documentReference = documentSnapshot.getDocumentReference()
                        ExerciseModel exercise = documentSnapshot.toObject(ExerciseModel.class);
                        exerciseList.add(exercise);
                    }
                    createGraph();
                }
                else{
                    Log.d("test","test");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        if (parent.getItemAtPosition(pos) != null) {
            exercise = parent.getItemAtPosition(pos).toString();
            exercisesCollection = firestoreDb.collection(exercise);
            createFirestoreReadListener();


        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


   public void createGraph(){



        //Array med verdier per øvelse
        ArrayList<Integer> progress = getExerciseValueTwoWeeks();

        //En Key-Value array
        DataPoint[] dataPoints = new DataPoint[progress.size()];

        //En Foreach loop har ikke index som krever en counter utafor.
       //dataPoints[count] bestemmer posisjon i arrayen.

       //new DataPoint(Key = count, progress.item.value = val)
        int count = 0;
        for(int val : progress){
            dataPoints[count] = new DataPoint(count, val);
            count++;
        }

        //progress.get(0) skal da matche dataPoints[0].getValue()
        lineSeriesTest = new LineGraphSeries<>(dataPoints);

        linegraph.removeAllSeries();
        linegraph.addSeries(lineSeriesTest);

    }
}