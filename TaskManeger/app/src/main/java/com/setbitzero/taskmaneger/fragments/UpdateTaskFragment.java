package com.setbitzero.taskmaneger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.setbitzero.taskmaneger.databinding.FragmentUpdateTaskBinding;

import java.util.ArrayList;

public class UpdateTaskFragment extends Fragment{
    FragmentUpdateTaskBinding binding;
    ArrayList<String> list;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateTaskBinding.inflate(inflater, container, false);
        //list = new ArrayList<>();

        if(getArguments() != null){
            list = getArguments().getStringArrayList("task-data");
            binding.taskTitle.setText(list.get(0));
            binding.taskDescription.setText(list.get(1));
            binding.taskStartTime.setText(list.get(3));
            binding.taskEndTime.setText(list.get(4));
            Toast.makeText(getContext(), list.get(0), Toast.LENGTH_LONG).show();
        }


        return binding.getRoot();
    }

}