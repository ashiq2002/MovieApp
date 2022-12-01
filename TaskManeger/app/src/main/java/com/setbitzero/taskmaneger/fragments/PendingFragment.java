package com.setbitzero.taskmaneger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.setbitzero.taskmaneger.R;
import com.setbitzero.taskmaneger.adapter.CustomAdapter;
import com.setbitzero.taskmaneger.database.DatabaseHelper;
import com.setbitzero.taskmaneger.databinding.FragmentPendingBinding;
import com.setbitzero.taskmaneger.model.TaskModel;

import java.util.List;

public class PendingFragment extends Fragment {
    FragmentPendingBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPendingBinding.inflate(inflater, container, false);
        databaseHelper = DatabaseHelper.getInstance(getContext());

        ImageView imageView = binding.getRoot().findViewById(R.id.taskIcon);

        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomAdapter adapter = new CustomAdapter(getContext(), getPendingTask());
        binding.recycler.setAdapter(adapter);

        return binding.getRoot();
    }

    private List<TaskModel> getPendingTask(){
        return databaseHelper.getTaskDao().getPendingTask();
    }
}