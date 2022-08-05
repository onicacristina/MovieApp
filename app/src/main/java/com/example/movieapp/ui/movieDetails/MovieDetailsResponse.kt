package com.example.movieapp.ui.movieDetails

import com.example.movieapp.ui.genres.Genre
import com.google.gson.annotations.SerializedName


class MovieDetailsResponse (
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("adult")
    var adult: Boolean,

    @SerializedName("poster_path")
    var poster_path: String?,

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("backdrop_path")
    var backdrop_path: String?,

    @SerializedName("release_date")
    var release_date: String?,

    @SerializedName("vote_average")
    var vote_average: String?,

    var videos: VideoListResponse,

    @SerializedName("genres")
    var genres: List<Genre>,
)