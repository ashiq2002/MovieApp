package com.setbitzero.newsapp.api;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.setbitzero.newsapp.R;
import com.setbitzero.newsapp.interfaces.OnFetchNewsListener;
import com.setbitzero.newsapp.model.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestResponse {
    Context context;
    ApiController apiController = ApiController.getInstance();

    public RequestResponse(Context context) {
        this.context = context;
    }

    public void getNews(OnFetchNewsListener<NewsResponse> listener, String category, String query){
        Call<NewsResponse> call = apiController.getApi()
                .getHeadlineNews("us", category, query, context.getString(R.string.API_KEY));

        try {
            call.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                    if(!response.isSuccessful()) Log.wtf("Response", "Response issue");
                    if(response.body() != null) listener.onFetchNews(response.body().getArticles(), "Success");
                }

                @Override
                public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
