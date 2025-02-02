package com.example.workoutapp.session;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workoutapp.MainActivity;
import com.example.workoutapp.R;
import com.example.workoutapp.SessionActivity;
import com.example.workoutapp.databinding.FragmentHollowBinding;
import com.example.workoutapp.databinding.FragmentPlankBinding;
import com.example.workoutapp.databinding.FragmentPushupBinding;
import com.example.workoutapp.databinding.ProgressFragmentBinding;
import com.example.workoutapp.exercises.ExerciseModel;
import com.example.workoutapp.exercises.SessionModel;
import com.example.workoutapp.progress.ProgressFragment;
import com.example.workoutapp.progress.ProgressViewModel;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PlankFragment extends Fragment {

    private FragmentPlankBinding binding;

    public static PlankFragment newInstance() {
        return new PlankFragment();
    }

    private List<ExerciseModel> exerciseList;

    CountDownTimer countDownTimer;
    Boolean isRunning = false;
    Integer storeTime;


    private FirebaseFirestore firestoreDb;
    private CollectionReference exercisesCollection;

    @Override
    public void onResume() {
        super.onResume();
        binding.videoViewExercise.start();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        exerciseList = new ArrayList<>();
        firestoreDb = FirebaseFirestore.getInstance();

        exercisesCollection = firestoreDb.collection("plank");
        //Bindings
        binding = FragmentPlankBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.textViewTitle.setText("Plank");
        //Start Button Listener
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopTimer();
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        //Creates a Countdown Timer
        countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.textViewTimer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                binding.btnStart.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                storeTime = 60 - Integer.parseInt(binding.textViewTimer.getText().toString());
                binding.textViewStored.setText("You lasted: " + storeTime.toString() + " seconds!");
                binding.textViewTimer.setText("1:00");
                exercisesCollection.add(new ExerciseModel("plank",storeTime));
            }
        };


        return view;
    }



    //Logic for start button.
    public void startStopTimer() {
        if (!isRunning) {
            countDownTimer.start();
            binding.btnStart.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
            isRunning = true;
        } else {
            countDownTimer.cancel();
            binding.btnStart.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
            storeTime = 60 - Integer.parseInt(binding.textViewTimer.getText().toString());
            binding.textViewStored.setText("You lasted: " + storeTime.toString() + " seconds!");
            binding.textViewTimer.setText("1:00");
            isRunning = false;
            exercisesCollection.add(new ExerciseModel("plank",storeTime));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Starts playing video.
        binding.videoViewExercise.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        String path = "android.resource://com.example.workoutapp/" + R.raw.cats;
        Uri u = Uri.parse(path);

        binding.videoViewExercise.setVideoURI(u);
        binding.videoViewExercise.requestFocus();
        binding.videoViewExercise.start();

    }
}