package com.setbitzero.taskmaneger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.setbitzero.taskmaneger.database.DatabaseHelper;
import com.setbitzero.taskmaneger.databinding.FragmentRunningBinding;
import com.setbitzero.taskmaneger.databinding.ItemBinding;
import com.setbitzero.taskmaneger.interfaces.OnSendDataClickListener;
import com.setbitzero.taskmaneger.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class RunningFragment extends Fragment {
   FragmentRunningBinding binding;
   DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRunningBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = DatabaseHelper.getInstance(getContext());

        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomAdapter adapter = new CustomAdapter(getContext(), getRunningTask());
        binding.recycler.setAdapter(adapter);
    }

    private List<TaskModel> getRunningTask(){
        return databaseHelper.getTaskDao().getRunningTask();
    }

}