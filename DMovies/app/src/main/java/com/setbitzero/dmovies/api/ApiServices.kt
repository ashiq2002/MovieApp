package com.setbitzero.dmovies.api

import com.setbitzero.dmovies.model.MovieDetail
import com.setbitzero.dmovies.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    //get all popular movie data
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key:String, @Query("page") page:Int):Response<MovieModel>

    //get all top_rated movie data
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") api_key:String, @Query("page") page:Int):Response<MovieModel>

    //get all now playing movie data
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") api_key:String, @Query("page") page:Int):Response<MovieModel>

    //get all upcoming movie data
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key:String, @Query("page") page:Int):Response<MovieModel>

    //get movie details by movie id
    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id:Int, @Query("api_key") api_key: String,
        @Query("append_to_response") video:String = "Videos"):Response<MovieDetail>

    //search movies
    @GET("search/movie")
    suspend fun searchMovie(@Query("api_key") api_key: String, @Query("query") query:String):Response<MovieModel>

}