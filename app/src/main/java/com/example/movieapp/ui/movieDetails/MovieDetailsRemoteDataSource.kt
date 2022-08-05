package com.example.movieapp.ui.movieDetails

import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Constants.APPEND_TO_RESPONSE
import retrofit2.Retrofit

class MovieDetailsRemoteDataSource (retrofit: Retrofit) {
    private val apiService: MovieDetailsAPIService = retrofit.create(MovieDetailsAPIService::class.java)
    private val movieDetailsMapper = MovieDetailsMapper()


    fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails( movieId, Constants.API_KEY, Constants.LANGUAGE, APPEND_TO_RESPONSE)
            .executeAndDeliver()
            .let { this.movieDetailsMapper.map(it) }    }

}