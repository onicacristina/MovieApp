package com.example.movieapp.ui.movieDetails

import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.utils.Constants
import retrofit2.Retrofit

class MovieDetailsRemoteDataSource (retrofit: Retrofit) {
    private val apiService: MovieDetailsAPIService = retrofit.create(MovieDetailsAPIService::class.java)
    private val movieDetailsMapper = MovieDetailsMapper()


    fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(Constants.API_KEY, Constants.LANGUAGE, "videos", movieId)
            .executeAndDeliver()
            .let { this.movieDetailsMapper.map(it) }    }

}