package com.example.movieapp.ui.movieDetails

import com.example.movieapp.ui.movies.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsAPIService {
    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String,
        @Path("movie_id") movieId: Int
    ) : Call<MovieDetailsResponse>
}