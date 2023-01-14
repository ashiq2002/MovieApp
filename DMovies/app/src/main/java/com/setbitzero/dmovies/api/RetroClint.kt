package com.setbitzero.dmovies.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClint {
    private const val BASE_URL:String = "https://api.themoviedb.org/3/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    //getting all retro services
    val apiServices: ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }
}