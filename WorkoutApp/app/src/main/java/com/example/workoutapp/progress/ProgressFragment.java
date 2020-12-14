package com.example.workoutapp.progress;

import androidx.lifecycle.ViewModelProvider;
import java.util.Calendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workoutapp.MainActivity;
import com.example.workoutapp.R;
import com.example.workoutapp.databinding.HomeFragmentBinding;
import com.example.workoutapp.databinding.ProgressFragmentBinding;
import com.example.workoutapp.exercises.ExerciseModel;
import com.example.workoutapp.exercises.TdeeModel;
import com.example.workoutapp.settings.SettingsViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ProgressFragment extends Fragment {

    private ProgressViewModel mViewModel;
    private ProgressFragmentBinding binding;
    private FirebaseFirestore firestoreDb;
    private FirebaseAuth mAuth;


    private CollectionReference hollowCollection;
    private CollectionReference plankCollection;
    private CollectionReference pushupsCollection;
    private CollectionReference tdeeCollection;

    private List<ExerciseModel> hollowList;
    private List<ExerciseModel> plankList;
    private List<ExerciseModel> pushupsList;
    private List<TdeeModel> tdeeList;
    TextView username;
    TextView btnTdee;
    TextView favorite;

    public static ProgressFragment newInstance() {
        return new ProgressFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProgressFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        btnTdee = binding.textViewTdee;
        favorite = binding.textViewExercise;
        username = binding.textViewWelcome;
        firestoreDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        hollowCollection = firestoreDb.collection("hollow");
        plankCollection = firestoreDb.collection("plank");
        pushupsCollection = firestoreDb.collection("pushups");
        tdeeCollection = firestoreDb.collection("tdee");
        hollowList = new ArrayList<>();
        plankList = new ArrayList<>();
        pushupsList = new ArrayList<>();
        tdeeList = new ArrayList<>();

        createFirestoreReadListener();



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProgressViewModel.class);

        if (hollowList.size() > plankList.size() && hollowList.size() > pushupsList.size()) {
            favorite.setText("Your favorite exercise is the Hollow hold");
        } else if (plankList.size() > hollowList.size() && plankList.size() > pushupsList.size()) {
            favorite.setText("Your favorite exercise is the Plank");
        } else {
            favorite.setText("Your favorite exercise is pushups");
        }
     try{
        if (mAuth.getCurrentUser() != null){
            FirebaseUser user = mAuth.getCurrentUser();
            String name = "Welcome! " + user.getDisplayName();
            username.setText(name);
        }
    }   catch (Exception e){
         throw e;
     }
        }





    private void createFirestoreReadListener() {
        hollowCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        //DocumentReference documentReference = documentSnapshot.getDocumentReference()
                        ExerciseModel exercise = documentSnapshot.toObject(ExerciseModel.class);
                        hollowList.add(exercise);
                    }
                }
                else{
                    Log.d("hollow","hollow failed");
                }
            }
        });

        plankCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        //DocumentReference documentReference = documentSnapshot.getDocumentReference()
                        ExerciseModel exercise = documentSnapshot.toObject(ExerciseModel.class);
                        plankList.add(exercise);
                    }
                }
                else{
                    Log.d("hollow","hollow failed");
                }
            }
        });

        pushupsCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        //DocumentReference documentReference = documentSnapshot.getDocumentReference()
                        ExerciseModel exercise = documentSnapshot.toObject(ExerciseModel.class);
                        pushupsList.add(exercise);
                    }
                }
                else{
                    Log.d("hollow","hollow failed");
                }
            }
        });

        tdeeCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        //DocumentReference documentReference = documentSnapshot.getDocumentReference()
                        TdeeModel exercise = documentSnapshot.toObject(TdeeModel.class);
                        tdeeList.add(exercise);

                    }
                }
                else{
                    Log.d("hollow","hollow failed");
                }
                int test = tdeeList.get(tdeeList.size()-1).getTdee();
                btnTdee.setText("Daily Caloric Intake " + test);

            }
        });

    }

}