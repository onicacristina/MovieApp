package com.example.movieapp.ui.movieDetails

import com.example.movieapp.ui.movies.MovieResponse
import com.google.gson.annotations.SerializedName

class VideoListResponse (
    @SerializedName("results")
    val movies: List<VideoResponse>
){
}