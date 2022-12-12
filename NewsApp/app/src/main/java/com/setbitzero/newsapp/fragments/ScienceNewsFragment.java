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
import com.setbitzero.newsapp.databinding.FragmentScienceNewsBinding;
import com.setbitzero.newsapp.interfaces.OnFetchNewsListener;
import com.setbitzero.newsapp.interfaces.OnNewsClickListener;
import com.setbitzero.newsapp.model.NewsArticles;
import com.setbitzero.newsapp.model.NewsResponse;

import java.util.List;

public class ScienceNewsFragment extends Fragment implements OnNewsClickListener {
    FragmentScienceNewsBinding binding;
    RequestResponse response;
    NewsAdapter adapter;
    ProgressDialog progressDialog;
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScienceNewsBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setTitle("Fetching Science News...");
        progressDialog.show();

        //get api response
        response = new RequestResponse(getContext());
        response.getNews(listener, "science", null);
    }

    //handle news response
    private final OnFetchNewsListener<NewsResponse> listener = new OnFetchNewsListener<NewsResponse>() {
        @Override
        public void onFetchNews(List<NewsArticles> list, String message) {
            showScienceNews(list);
            progressDialog.dismiss();
        }

        @Override
        public void onError(String Error) {
            Log.wtf("ReError", Error);
        }
    };

    //set all adapter
    private void showScienceNews(List<NewsArticles> list){
        binding.scienceNewsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext(), list, this);
        binding.scienceNewsRecycler.setAdapter(adapter);

    }

    @Override
    public void onNewsClick(NewsArticles articles) {
        ScienceNewsFragmentDirections.ActionScienceNewsFragment2ToDetailNewsFragment action = ScienceNewsFragmentDirections
                .actionScienceNewsFragment2ToDetailNewsFragment(articles);
        navController.navigate(action);
    }
}