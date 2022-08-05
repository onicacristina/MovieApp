package com.example.movieapp.ui.searchScreen.ui.savedMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.ui.movies.Movie
import com.example.movieapp.ui.movies.MovieRepository
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WatchedAdapter(private val watchedMoviesList: MutableList<Movie>) :
    RecyclerView.Adapter<WatchedAdapter.ViewHolder>(){

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val movieTitle: TextView = view.findViewById(R.id.tv_movie_title)
            val movieDescription: TextView = view.findViewById(R.id.tv_movie_description)
            val parentView: LinearLayout = view.findViewById(R.id.ll_parent)
            val moviePhoto: ImageView = view.findViewById(R.id.iv_movie)
            val watchedMovieIcon: ImageButton = view.findViewById(R.id.ib_delete_movie)
        }

    private var movieRepository = MovieRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_saved_movie, parent, false)

        return WatchedAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = watchedMoviesList[position]
        holder.movieTitle.text = movie.title + " (" + movie.release_date +")"
        holder.movieDescription.text = movie.overview
        if(movie.backdrop_path != null)
            Glide.with(holder.moviePhoto.context).load(Constants.IMAGE_URL_MOVIE + movie.poster_path).into(holder.moviePhoto)

        holder.watchedMovieIcon.setOnClickListener{
            movie.isWatched = !movie.isWatched
            insertOrReplaceMovie(movie)
            watchedMoviesList.removeAt(position)
            notifyDataSetChanged()
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, watchedMoviesList.size)
        }
    }

    private fun insertOrReplaceMovie(movie: Movie){
        GlobalScope.launch(Dispatchers.IO) {
            movieRepository.insertOrReplace(movie)
        }
    }

    override fun getItemCount(): Int = watchedMoviesList.size
}