package com.example.movieapp.ui.movies

data class Movie (
    var id: Int,
    var title: String,
    var adult: Boolean,
    var poster_path: String,
    var overview: String,
    var backdrop_path: String,
    var release_date: String
        ) {
}