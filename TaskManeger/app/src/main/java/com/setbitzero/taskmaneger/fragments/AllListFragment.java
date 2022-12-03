package com.setbitzero.taskmaneger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.setbitzero.taskmaneger.adapter.CustomAdapter;
import com.setbitzero.taskmaneger.database.DatabaseHelper;
import com.setbitzero.taskmaneger.databinding.FragmentAllListBinding;
import com.setbitzero.taskmaneger.interfaces.OnSendDataClickListener;
import com.setbitzero.taskmaneger.model.TaskModel;

import java.util.List;


public class AllListFragment extends Fragment {
    FragmentAllListBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = DatabaseHelper.getInstance(getContext());

        binding.recycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        CustomAdapter adapter = new CustomAdapter(getContext(), getData());
        binding.recycler.setAdapter(adapter);
    }

    private List<TaskModel> getData(){
        return databaseHelper.getTaskDao().getAllData();
    }
}