package com.example.workoutapp.home;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoutapp.GoogleActivity;
import com.example.workoutapp.GraphActivity;
import com.example.workoutapp.MainActivity;
import com.example.workoutapp.SessionActivity;
import com.example.workoutapp.TdeeActivity;
import com.example.workoutapp.WebActivity;
import com.example.workoutapp.YoutubeActivity;
import com.example.workoutapp.databinding.HomeFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    ConnectivityManager connectivityManager;
    boolean connected = false;
    public HomeFragmentBinding getBinding() {
        return binding;
    }

    private HomeFragmentBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        connectivityManager = (ConnectivityManager)getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            connected = true;
        }
        else {

            connected = false;
        }


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
                Intent intent = new Intent(getActivity(), GoogleActivity.class);
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
                Intent intent = new Intent(getActivity(), WebActivity.class);
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