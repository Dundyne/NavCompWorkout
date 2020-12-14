package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import com.example.workoutapp.databinding.ActivityTdeeBinding;
import com.example.workoutapp.exercises.ExerciseModel;
import com.example.workoutapp.exercises.TdeeModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

public class TdeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ActivityTdeeBinding binding;
    EditText weight, height, age;
    Boolean gender; // true male, false female
    TextView results;
    private FirebaseFirestore firestoreDb;
    private CollectionReference exercisesCollection;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTdeeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firestoreDb = FirebaseFirestore.getInstance();
        spinner = binding.spinner;
        exercisesCollection = firestoreDb.collection("tdee");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        binding.BtnTDEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcTDEE();
            }
        });

    }

    public void calcTDEE(){
        weight = binding.WeightTxt;
        height = binding.HeightTxt;
        age = binding.AgeTxt;
        results = binding.ResultsTDEE;

        int parsedWeight = Integer.parseInt(weight.getText().toString());
        int parsedHeight = Integer.parseInt(height.getText().toString());
        int parsedAge = Integer.parseInt(age.getText().toString());

        int s;
        if (gender) {
            s = 5;
        }
        else {
            s = -161;
        }
        float resultsFloat = (10 * parsedWeight) + (6.25f * parsedHeight) - (5 * parsedAge) + s;


        exercisesCollection.add(new TdeeModel((int)resultsFloat));

        results.setText(String.valueOf(resultsFloat) + " Calories Daily");
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        if (parent.getItemAtPosition(pos).toString().equals("Male")) {
            gender = true;
        } else gender = false;
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}