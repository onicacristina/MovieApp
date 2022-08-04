package com.example.movieapp.ui.movieDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.movies.MovieAdapter

class MovieGenresAdapter(private val movieGenresList: List<Genre>) :
RecyclerView.Adapter<MovieGenresAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvGenres : TextView = view.findViewById(R.id.tv_genres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genres_movie, parent, false)

        return MovieGenresAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = movieGenresList[position]
        holder.tvGenres.text = genre.name
    }

    override fun getItemCount(): Int = movieGenresList.size
}