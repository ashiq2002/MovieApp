package com.setbitzero.dmovies.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.setbitzero.dmovies.repository.MovieRepository
import com.setbitzero.dmovies.ui.home.HomeViewModel
import java.lang.reflect.Constructor

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val repository: MovieRepository)
    :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }

}