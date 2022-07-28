package com.example.movieapp.ui.movies

import com.example.movieapp.network.APIClient

class MovieRepository private constructor() {
    companion object{
        val instance = MovieRepository()
    }

    private val movieRemoteDataSource = MovieRemoteDataSource(APIClient.instance.retrofit)

    fun getALLRemoteMovies() = movieRemoteDataSource.getMovies()
}