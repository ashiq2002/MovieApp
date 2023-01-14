package com.setbitzero.dmovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setbitzero.dmovies.key.Keys
import com.setbitzero.dmovies.model.MovieDetail
import com.setbitzero.dmovies.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository, private val id:Int) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieDetails(id, Keys.MDB_KEY, "videos")
        }
    }

    //get movie detail data from repository class
    val movieDetail:LiveData<MovieDetail>
    get() = repository.movieDetails

}