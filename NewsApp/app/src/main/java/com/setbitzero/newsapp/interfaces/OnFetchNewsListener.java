package com.setbitzero.newsapp.interfaces;

import com.setbitzero.newsapp.model.NewsArticles;

import java.util.List;

public interface OnFetchNewsListener <NewsResponse>{
    void onFetchNews(List<NewsArticles> list, String message);
    void onError(String Error);
}
