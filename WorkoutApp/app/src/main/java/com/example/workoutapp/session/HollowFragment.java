package com.example.workoutapp.session;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workoutapp.R;
import com.example.workoutapp.databinding.FragmentHollowBinding;
import com.example.workoutapp.databinding.ProgressFragmentBinding;
import com.example.workoutapp.progress.ProgressFragment;
import com.example.workoutapp.progress.ProgressViewModel;

public class HollowFragment extends Fragment {

    private FragmentHollowBinding binding;
    public static HollowFragment newInstance() {
        return new HollowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHollowBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}