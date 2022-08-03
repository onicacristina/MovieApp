package com.example.movieapp.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.ui.movies.Movie


class DetailsViewModel : ViewModel() {
    private val currentMovie = MutableLiveData<Movie?>()

    fun getCurrentMovie() = currentMovie

    fun setCurrentMovie(movie: Movie?) {
        currentMovie.value = movie
    }
}