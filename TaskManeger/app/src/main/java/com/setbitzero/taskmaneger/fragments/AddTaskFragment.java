package com.setbitzero.taskmaneger.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.google.android.material.button.MaterialButton;
import com.setbitzero.taskmaneger.R;
import com.setbitzero.taskmaneger.activities.MainActivity;
import com.setbitzero.taskmaneger.databinding.ActivityMainBinding;
import com.setbitzero.taskmaneger.databinding.FragmentAddTaskBinding;

import java.util.Calendar;

public class AddTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    FragmentAddTaskBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskBinding.inflate(inflater, container, false);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                new String[]{"Update", "Delete", "Complete"});
        binding.spinner.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

}