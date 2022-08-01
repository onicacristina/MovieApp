package com.example.movieapp.ui.movies

import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.utils.Constants.API_KEY
import com.example.movieapp.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class MovieRemoteDataSource (retrofit: Retrofit) {
    private val apiService: MovieAPIService = retrofit.create(MovieAPIService::class.java)
    private val movieMapper = MovieMapper()


    fun getMovies(withCast: String, withGenres: String): List<Movie> {
        return apiService.getMovies(API_KEY, LANGUAGE, withCast, withGenres)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it)}
    }

    fun getSearchedMovie(query: String): List<Movie> {
        return apiService.getSearchedMovie(API_KEY, LANGUAGE, query)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }
}