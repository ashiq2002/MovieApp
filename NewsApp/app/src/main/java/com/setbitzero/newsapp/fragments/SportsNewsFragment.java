package com.setbitzero.newsapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.setbitzero.newsapp.R;
import com.setbitzero.newsapp.adapter.NewsAdapter;
import com.setbitzero.newsapp.api.RequestResponse;
import com.setbitzero.newsapp.databinding.FragmentSportsNewsBinding;
import com.setbitzero.newsapp.interfaces.OnFetchNewsListener;
import com.setbitzero.newsapp.interfaces.OnNewsClickListener;
import com.setbitzero.newsapp.model.NewsArticles;
import com.setbitzero.newsapp.model.NewsResponse;

import java.util.List;

public class SportsNewsFragment extends Fragment implements OnNewsClickListener {
    FragmentSportsNewsBinding binding;
    RequestResponse response;
    NewsAdapter adapter;
    ProgressDialog progressDialog;
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSportsNewsBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setTitle("Fetching Sports News...");
        progressDialog.show();

        //get api response
        response = new RequestResponse(getContext());
        response.getNews(listener, "sports", null);
    }

    //handle news response
    private final OnFetchNewsListener<NewsResponse> listener = new OnFetchNewsListener<NewsResponse>() {
        @Override
        public void onFetchNews(List<NewsArticles> list, String message) {
            showSportsNews(list);
            progressDialog.dismiss();
        }

        @Override
        public void onError(String Error) {
            Log.wtf("ReError", Error);
        }
    };

    //set all adapter
    private void showSportsNews(List<NewsArticles> list){
        binding.sportsNewsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext(), list, this);
        binding.sportsNewsRecycler.setAdapter(adapter);

    }

    @Override
    public void onNewsClick(NewsArticles articles) {
        SportsNewsFragmentDirections.ActionSportsNewsFragment2ToDetailNewsFragment action = SportsNewsFragmentDirections
                .actionSportsNewsFragment2ToDetailNewsFragment(articles);
        navController.navigate(action);
    }
}