package com.setbitzero.dmovies.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.setbitzero.dmovies.repository.MovieRepository
import com.setbitzero.dmovies.ui.detail.MovieDetailViewModel

@Suppress("UNCHECKED_CAST")
class MovieDetailViewModelFactory(private val repository: MovieRepository, private val id:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MovieDetailViewModel(repository, id) as T
    }
}