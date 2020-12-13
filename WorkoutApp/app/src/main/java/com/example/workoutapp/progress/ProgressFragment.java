package com.example.workoutapp.progress;

import androidx.lifecycle.ViewModelProvider;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workoutapp.MainActivity;
import com.example.workoutapp.R;
import com.example.workoutapp.databinding.HomeFragmentBinding;
import com.example.workoutapp.databinding.ProgressFragmentBinding;
import com.example.workoutapp.settings.SettingsViewModel;

import java.util.List;
import java.util.Locale;

public class ProgressFragment extends Fragment {

    private ProgressViewModel mViewModel;
    private ProgressFragmentBinding binding;
    public static ProgressFragment newInstance() {
        return new ProgressFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProgressFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Geocoder geocoder = new Geocoder(this.getContext(), Locale.getDefault());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProgressViewModel.class);
    }

}