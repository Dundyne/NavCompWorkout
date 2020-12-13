package com.example.workoutapp.home;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workoutapp.GraphActivity;
import com.example.workoutapp.SessionActivity;
import com.example.workoutapp.TdeeActivity;
import com.example.workoutapp.YoutubeActivity;
import com.example.workoutapp.databinding.HomeFragmentBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;

    private final int RC_SIGN_IN = 1;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SessionActivity.class);
                startActivity(intent);
            }
        });

        binding.imageBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.imageBtnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TdeeActivity.class);
                startActivity(intent);
            }
        });
        binding.imageBtnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraphActivity.class);
                startActivity(intent);
            }
        });

        binding.imageBtnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), YoutubeActivity.class);
                startActivity(intent);
            }
        });




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }


}