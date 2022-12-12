package com.setbitzero.newsapp.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import com.setbitzero.newsapp.R;
import com.setbitzero.newsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigateBottomBar();
    }

    @SuppressLint("NonConstantResourceId")
    private void navigateBottomBar() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.homeItem:
                    Navigation.findNavController( this, R.id.nav_controller).navigate(R.id.homeFragment2);
                    return true;

                case R.id.sportsItem:
                    Navigation.findNavController( this, R.id.nav_controller).navigate(R.id.sportsNewsFragment2);
                    return true;

                case R.id.scienceItem:
                    Navigation.findNavController( this, R.id.nav_controller).navigate(R.id.scienceNewsFragment2);
                    return true;

                case R.id.technologyItem:
                    Navigation.findNavController( this, R.id.nav_controller).navigate(R.id.techNewsFragment);
                    return true;

                default:
                    return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}