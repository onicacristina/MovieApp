package com.example.movieapp.ui.movies

import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.ui.movieDetails.DetailsViewModel
import com.example.movieapp.ui.movieDetails.MovieDetailsFragment
import com.example.movieapp.ui.movieDetails.MovieDetailsRepository
import com.example.movieapp.utils.Constants.IMAGE_URL_MOVIE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieAdapter(private val movieList: List<Movie>,
                   listener: ItemClickListener,
                   private val detailsCallBack: (() ->Unit)?,
                    private val viewModel: DetailsViewModel):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val movieTitle: TextView = view.findViewById(R.id.tv_movie_title)
            val movieDescription: TextView = view.findViewById(R.id.tv_movie_description)
            val parentView: LinearLayout = view.findViewById(R.id.ll_parent)
            val moviePhoto: ImageView = view.findViewById(R.id.iv_movie)
            val savedMovieIcon: ImageButton = view.findViewById(R.id.ib_saved_movie)
            val watchedMovieIcon: ImageButton = view.findViewById(R.id.ib_watched_movie)

            val favorite: Boolean = false
            val watched: Boolean = false

        }

    private var movieRepository = MovieRepository.instance


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.movieTitle.text = movie.title + " (" + movie.release_date +")"
        holder.movieDescription.text = movie.overview
        if(movie.backdrop_path!=null)
        Glide.with(holder.moviePhoto.context).load(IMAGE_URL_MOVIE + movie.poster_path).into(holder.moviePhoto)

        selectSavedMovie(holder, movie)
        holder.savedMovieIcon.setOnClickListener{
            movie.isSaved = !movie.isSaved
            selectSavedMovie(holder, movie)
            insertOrReplaceMovie(movie)
            Log.v("SearchMoviesFragment", "saved" + movie.isSaved +" " + movie.id);
        }

        selectWatchedMovie(holder, movie)
        holder.watchedMovieIcon.setOnClickListener {
            movie.isWatched = !movie.isWatched
            selectWatchedMovie(holder, movie)
            insertOrReplaceMovie(movie)
            Log.v("SearchMoviesFragment", "watched " + movie.isWatched +" " + movie.id);
        }

        holder.parentView.setOnClickListener{
            viewModel.setCurrentMovie(movie)
            detailsCallBack?.invoke()
        }
    }

    private fun insertOrReplaceMovie(movie: Movie){
        GlobalScope.launch(Dispatchers.IO) {
            movieRepository.insertOrReplace(movie)
        }
    }

    private fun selectSavedMovie(holder: ViewHolder, movie: Movie){

        holder.savedMovieIcon.setImageResource(
            when(movie.isSaved){
                true -> R.drawable.ic_baseline_favorite_24
                else -> R.drawable.ic_baseline_favorite_border_24
            }
        )
    }

    private fun selectWatchedMovie(holder: ViewHolder, movie: Movie){
        holder.watchedMovieIcon.setImageResource(
            when(movie.isWatched){
                true -> R.drawable.ic_baseline_turned_in_24
                else -> R.drawable.ic_baseline_turned_in_not_24
            }
        )
    }

    override fun getItemCount() = movieList.size
}