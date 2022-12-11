package com.setbitzero.newsapp.api;

import com.setbitzero.newsapp.interfaces.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static ApiController instance = null;
    private static Retrofit retrofit;

    ApiController(){
       try {
           retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public static synchronized ApiController getInstance(){
        return instance == null? instance = new ApiController():instance;
    }


    public ApiService getApi(){
        return retrofit.create(ApiService.class);
    }
}
