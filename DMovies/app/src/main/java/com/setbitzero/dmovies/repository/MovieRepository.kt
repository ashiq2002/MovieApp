package com.setbitzero.dmovies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.setbitzero.dmovies.api.ApiServices
import com.setbitzero.dmovies.model.MovieDetail
import com.setbitzero.dmovies.model.MovieModel

class MovieRepository(private val apiServices: ApiServices) {
    //popular movie data
    private val popularMovieLiveData = MutableLiveData<MovieModel>()
    val popularMovie:LiveData<MovieModel>
    get() = popularMovieLiveData

    //top rated movie data
    private val topMovieLiveData = MutableLiveData<MovieModel>()
    val topRateMovie:LiveData<MovieModel>
        get() = topMovieLiveData

    //now playing movie data
    private val nowPlayingMovieLiveData = MutableLiveData<MovieModel>()
    val nowPlayingMovie:LiveData<MovieModel>
        get() = nowPlayingMovieLiveData

    //upcoming movie data
    private val upcomingMovieLiveData = MutableLiveData<MovieModel>()
    val upcomingMovie:LiveData<MovieModel>
        get() = upcomingMovieLiveData

    //movie details data
    private val movieDetailLiveData = MutableLiveData<MovieDetail>()
    val movieDetails:LiveData<MovieDetail>
        get() = movieDetailLiveData

    //search Movie data
    private val searchMovieLiveData = MutableLiveData<MovieModel>()
    val searchMovie:LiveData<MovieModel>
        get() = searchMovieLiveData

    //get popular movies and post into movieModelLiveData
    suspend fun getPopularMovie(api_key:String, page:Int){
        val result = apiServices.getPopularMovies(api_key, page)
        if(result.isSuccessful){
            if(result?.body() != null){
                popularMovieLiveData.postValue(result.body())
            }
        }
    }


    //get topRated movie and post into movieModelLiveData
    suspend fun getTopRatedMovies(api_key: String, page: Int){
        val result = apiServices.getTopRatedMovies(api_key, page)
        if(result.isSuccessful){
            if(result?.body() != null){
                topMovieLiveData.postValue(result.body())
            }
        }
    }

    //get now playing movie and post into movieModelLiveData
    suspend fun getNowPlayingMovies(api_key: String, page: Int){
        val result = apiServices.getNowPlayingMovies(api_key, page)
        if(result.isSuccessful){
            if(result?.body() != null){
                nowPlayingMovieLiveData.postValue(result.body())
            }
        }
    }

    //get upcoming movie and post into upcomingMovieLiveData
    suspend fun getUpcomingMovies(api_key:String, page: Int){
        val result = apiServices.getUpcomingMovies(api_key, page)
        if(result.isSuccessful){
            if(result?.body() != null){
                upcomingMovieLiveData.postValue(result.body())
            }
        }
    }

    //get movie details data
    suspend fun getMovieDetails(id:Int, api_key: String, append_to_response:String){
        val result = apiServices.getMovieDetails(id, api_key, append_to_response)
        if(result.isSuccessful){
            if(result?.body() != null){
                movieDetailLiveData.postValue(result.body())
            }
        }
    }

    //get upcoming movie and post into upcomingMovieLiveData
    suspend fun getMovieSearchResult(api_key:String, query: String){
        val result = apiServices.searchMovie(api_key, query)
        if(result.isSuccessful){
            if(result?.body() != null){
                searchMovieLiveData.postValue(result.body())
            }
        }
    }

}