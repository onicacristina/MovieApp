package com.example.movieapp.ui.movieDetails

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient
import com.example.movieapp.ui.movies.MovieLocalDataSource
import com.example.movieapp.ui.movies.MovieRemoteDataSource
import com.example.movieapp.ui.movies.MovieRepository

class MovieDetailsRepository {
    companion object{
        val instance = MovieDetailsRepository()
    }

    private val movieDetailsRemoteDataSource = MovieDetailsRemoteDataSource(APIClient.instance.retrofit)
    private val movieDetailsLocalDataSource = MovieDetailsLocalDataSource(Database.instance)

    fun getMovieDetails(movieId: Int) = movieDetailsRemoteDataSource.getMovieDetails(movieId)
}