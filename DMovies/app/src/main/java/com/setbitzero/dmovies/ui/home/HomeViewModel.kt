package com.setbitzero.dmovies.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setbitzero.dmovies.key.Keys
import com.setbitzero.dmovies.model.MovieModel
import com.setbitzero.dmovies.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    //getting popular movies
    fun getPopularMovie(page: Int): LiveData<MovieModel> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPopularMovie(Keys.MDB_KEY, page)
        }
        return repository.popularMovie
    }//end of get popular movie function

    //getting top rated movies
    fun getTopRatedMovie(page: Int): LiveData<MovieModel> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTopRatedMovies(Keys.MDB_KEY, page)
        }
        return repository.topRateMovie
    }//end of top rated movie function

    //getting now playing movies
    fun getNowPlayingMovie(page: Int): LiveData<MovieModel> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNowPlayingMovies(Keys.MDB_KEY, page)
        }
        return repository.nowPlayingMovie
    }//end of now playing movie function

    //getting upcoming movies
    fun getUpComingMovie(page: Int): LiveData<MovieModel> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUpcomingMovies(Keys.MDB_KEY, page)
        }
        return repository.upcomingMovie
    }//end of upcoming movie function

    //getting movie search result
    fun getMovieSearchResult(query:String): LiveData<MovieModel>{
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieSearchResult(Keys.MDB_KEY, query)
        }
        return repository.searchMovie
    }
}