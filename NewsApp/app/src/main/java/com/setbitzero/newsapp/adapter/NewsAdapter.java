package com.setbitzero.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.setbitzero.newsapp.databinding.ItemBinding;
import com.setbitzero.newsapp.interfaces.OnNewsClickListener;
import com.setbitzero.newsapp.model.NewsArticles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    Context context;
    List<NewsArticles> articles;
    OnNewsClickListener newsClickListener;

    public NewsAdapter(Context context, List<NewsArticles> articles, OnNewsClickListener newsClickListener) {
        this.context = context;
        this.articles = articles;
        this.newsClickListener = newsClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.binding.newsSource.setText(articles.get(position).getNewsSource().getName());
        holder.binding.newsTitle.setText(articles.get(position).getTitle());
        holder.binding.newsDescription.setText(articles.get(position).getDescription());

        if(articles.get(position).getUrlToImage() != null){
            Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.binding.newsImage);
        }
        holder.binding.cardView.setOnClickListener(v->newsClickListener.onNewsClick(articles.get(position)));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBinding binding;

        public ViewHolder(@NonNull ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
