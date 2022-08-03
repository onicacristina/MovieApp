package com.example.movieapp.ui.searchScreen.ui.savedMovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.genres.GenresAdapter
import com.example.movieapp.ui.movies.Movie
import com.example.movieapp.ui.movies.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteFragment: Fragment(R.layout.fragment_favorite_list) {

    private var movies: List<Movie> = emptyList()
    private val movieRepository = MovieRepository.instance

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavoriteMovies(view)
    }

    private fun getFavoriteMovies(view: View){
        GlobalScope.launch (Dispatchers.IO) {
            movies = movieRepository.getSavedMovies()
            withContext(Dispatchers.Main){
                setupRecyclerView(view)
            }
        }
    }

    private fun setupRecyclerView(view: View){
        val rvFavoriteMovies = view?.findViewById<RecyclerView>(R.id.rv_favorite_movies)
        rvFavoriteMovies?.layoutManager=
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        rvFavoriteMovies?.adapter = FavoriteAdapter(movies)
    }
}