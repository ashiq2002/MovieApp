package com.setbitzero.dmovies

import android.app.Application
import com.setbitzero.dmovies.api.ApiServices
import com.setbitzero.dmovies.api.RetroClint
import com.setbitzero.dmovies.repository.MovieRepository

class MovieApplication:Application() {
    lateinit var repository: MovieRepository

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        val apiServices:ApiServices = RetroClint.apiServices
        repository = MovieRepository(apiServices)
    }
}