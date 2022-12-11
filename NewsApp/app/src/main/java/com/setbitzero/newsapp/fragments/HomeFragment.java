package com.setbitzero.newsapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.setbitzero.newsapp.adapter.NewsAdapter;
import com.setbitzero.newsapp.api.RequestResponse;
import com.setbitzero.newsapp.databinding.FragmentHomeBinding;
import com.setbitzero.newsapp.interfaces.OnFetchNewsListener;
import com.setbitzero.newsapp.model.NewsArticles;
import com.setbitzero.newsapp.model.NewsResponse;

import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    RequestResponse response;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        response = new RequestResponse(getContext());
        response.getNews(listener, "sports", null);
        return binding.getRoot();
    }

    //handle
    private final OnFetchNewsListener<NewsResponse> listener = new OnFetchNewsListener<NewsResponse>() {
        @Override
        public void onFetchNews(List<NewsArticles> list, String message) {
            showAllNews(list);
        }

        @Override
        public void onError(String Error) {
            Log.wtf("ReError", Error);
        }
    };

    private void showAllNews(List<NewsArticles> list){
        binding.AllNewsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsAdapter adapter = new NewsAdapter(getContext(), list);
        binding.AllNewsRecycler.setAdapter(adapter);
    }
}