package com.example.movieapp.ui.searchScreen.ui.savedMovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.movies.Movie
import com.example.movieapp.ui.movies.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchedFragment: Fragment(R.layout.fragment_watched_list) {

    private var movies: List<Movie> = emptyList()
    private var movieRepository = MovieRepository.instance

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWatchedMovies(view)
    }

    private fun getWatchedMovies(view: View){
        GlobalScope.launch (Dispatchers.IO) {
            movies = movieRepository.getWatchedMovies()
            withContext(Dispatchers.Main){
                setUpRecyclerView(view)
            }
        }
    }

    private fun setUpRecyclerView(view: View){
        val rvWatchedMovies = view?.findViewById<RecyclerView>(R.id.rv_watched_movies)
        rvWatchedMovies?.layoutManager=
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        rvWatchedMovies?.adapter = WatchedAdapter(movies)
    }
}