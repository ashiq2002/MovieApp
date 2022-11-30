package com.setbitzero.taskmaneger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.setbitzero.taskmaneger.R;
import com.setbitzero.taskmaneger.activities.MainActivity;
import com.setbitzero.taskmaneger.adapter.CustomAdapter;
import com.setbitzero.taskmaneger.databinding.FragmentRunningBinding;
import com.setbitzero.taskmaneger.databinding.ItemBinding;

import java.util.ArrayList;

public class RunningFragment extends Fragment {
   FragmentRunningBinding binding;
   Spinner spinner;
   ArrayList<String> action;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRunningBinding.inflate(inflater, container, false);

        binding.recycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        recView();

        spinner = binding.getRoot().findViewById(R.id.itemAction);
        action = new ArrayList<>();
        action.add("Update");
        action.add("Delete");
        action.add("Complete");

        return binding.getRoot();
    }

    private void recView(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");
        list.add("Item 1");

        CustomAdapter adapter = new CustomAdapter(getActivity(), list);
        binding.recycler.setAdapter(adapter);
    }
}