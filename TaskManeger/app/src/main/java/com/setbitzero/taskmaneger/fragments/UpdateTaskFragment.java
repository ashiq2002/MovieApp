package com.setbitzero.taskmaneger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.setbitzero.taskmaneger.databinding.FragmentUpdateTaskBinding;

public class UpdateTaskFragment extends Fragment {
    FragmentUpdateTaskBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateTaskBinding.inflate(inflater, container, false);

        //Toast.makeText(getContext(), new TaskData().getTitle(), Toast.LENGTH_LONG).show();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}