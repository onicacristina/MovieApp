package com.example.movieapp.ui.movies

import com.example.movieapp.ui.genres.GenresListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("with_cast") withCast: String,
        @Query("with_genres") withGenres: String
    ) : Call<MovieListResponse>


    @GET("search/movie")
    fun getSearchedMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ) : Call<MovieListResponse>
}