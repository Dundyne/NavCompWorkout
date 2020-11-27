package com.example.workoutapp.session;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workoutapp.MainActivity;
import com.example.workoutapp.R;
import com.example.workoutapp.databinding.FragmentPlankBinding;
import com.example.workoutapp.databinding.FragmentPushupBinding;


public class PushupFragment extends Fragment {
    private FragmentPushupBinding binding;
    public static PushupFragment newInstance() {
        return new PushupFragment();
    }
    Integer counter;


    @Override
    public void onResume() {
        super.onResume();
        binding.videoViewExercise.start();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPushupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.textViewTitle.setText("Pushups");
        counter = 5;
        binding.textViewCount.setText(counter.toString());
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                binding.textViewCount.setText(counter.toString());
            }
        });

        binding.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                binding.textViewCount.setText(counter.toString());
            }
        });
        return view;
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