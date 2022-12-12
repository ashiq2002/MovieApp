package com.setbitzero.newsapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.setbitzero.newsapp.R;
import com.setbitzero.newsapp.databinding.FragmentDetailNewsBinding;
import com.setbitzero.newsapp.model.NewsArticles;
import com.squareup.picasso.Picasso;

public class DetailNewsFragment extends Fragment {
    FragmentDetailNewsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailNewsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //getting data from fragment
        if(getArguments() != null){
            DetailNewsFragmentArgs args = DetailNewsFragmentArgs.fromBundle(getArguments());
            NewsArticles articles = args.getNewsArticles();

            if(articles != null){
                //load and set image from online
                if(articles.getUrlToImage() != null)
                    Picasso.get().load(articles.getUrlToImage()).into(binding.newsImage);

                binding.publishDate.setText(articles.getPublishedAt());
                binding.newsAuthor.setText(articles.getAuthor());
                binding.newsTitle.setText(articles.getTitle());
                binding.newsContent.setText(articles.getContent());
            }

        }
    }
}