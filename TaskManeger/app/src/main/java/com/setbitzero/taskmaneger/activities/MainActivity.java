package com.setbitzero.taskmaneger.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.setbitzero.taskmaneger.R;
import com.setbitzero.taskmaneger.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigate();
        binding.addTask.setOnClickListener(v ->{
//                Navigation.findNavController(MainActivity.this, R.id.nav_controller).navigate(R.id.addTaskFragment);
//                binding.navigationBar.setVisibility(View.GONE);
//                binding.cardView.setVisibility(View.GONE);
            startActivity(new Intent(MainActivity.this, AddTaskActivity.class));
        });

        binding.searchView.setOnSearchClickListener(v -> {
            binding.spinner.setVisibility(View.GONE);
            binding.searchView.setVisibility(View.VISIBLE);
        });

        binding.searchView.setOnCloseListener(() -> {
            binding.spinner.setVisibility(View.VISIBLE);
            return false;
        });

    }

    @SuppressLint("NonConstantResourceId")
    private void navigate() {
        binding.navigationBar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.allTask:
                    Navigation.findNavController(MainActivity.this, R.id.nav_controller).navigate(R.id.allListFragment);
                    break;

                case R.id.runningTask:
                    Navigation.findNavController(MainActivity.this, R.id.nav_controller).navigate(R.id.runningFragment2);
                    break;

                case R.id.pendingTask:
                    Navigation.findNavController(MainActivity.this, R.id.nav_controller).navigate(R.id.pendingFragment2);
                    break;

                case R.id.completeTask:
                    Navigation.findNavController(MainActivity.this, R.id.nav_controller).navigate(R.id.completeFragment);
                    break;

                case R.id.notification:
                    Navigation.findNavController(MainActivity.this, R.id.nav_controller).navigate(R.id.notificationFragment);
                    break;

                default:
            }

            return true;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        binding.navigationBar.setVisibility(View.VISIBLE);
        binding.topBar.setVisibility(View.VISIBLE);
    }

}