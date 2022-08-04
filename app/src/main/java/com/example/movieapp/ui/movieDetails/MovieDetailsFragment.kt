package com.example.movieapp.ui.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.utils.Constants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieDetailsFragment : Fragment() {

    private val movieDetailsRepository = MovieDetailsRepository.instance
    private lateinit var viewModel: DetailsViewModel
    private var genres : List<Genre> = emptyList()
    private var videos : List<Video> = emptyList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]
        val model = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]
        model.getCurrentMovie().observe(viewLifecycleOwner){ it?.let {
            val id: Int = it.id
            getMovie(id, view)
        }
        }
    }

    private fun getMovie(id: Int, view: View){
        GlobalScope.launch (Dispatchers.IO) {
            var movie = movieDetailsRepository.getMovieDetails(id)
            withContext(Dispatchers.Main){
                Log.d("TAG", "message " + movie.title)
                val tvTitle = view.findViewById<TextView>(R.id.tv_movie_title)
                tvTitle.text = movie.title
                 val videos : List<Video>? = movie.videos?.movies

                val tvDescription = view.findViewById<TextView>(R.id.tv_movie_description)
                tvDescription.text = movie.overview

                val ivPOster = view.findViewById<ImageView>(R.id.iv_movie_photo)
                Glide.with(ivPOster.context).load(Constants.IMAGE_URL_MOVIE + movie.poster_path).into(ivPOster)
                 genres = movie.genres

                val ytbVideo = view.findViewById<YouTubePlayerView>(R.id.ytbVideo)

                lifecycle.addObserver(ytbVideo)

                ytbVideo.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        val videoId : String? = videos?.get(0)?.key
                        youTubePlayer.loadVideo(videoId.toString(), 0f)
                    }
                })

                Log.d("TAG", "message key:  " + videos?.get(0)?.key)
                Log.d("TAG", "message genre:  " + genres[0].name)


                setupRecyclerView(view)
            }
        }

    }

    private fun setupRecyclerView(view: View){
        val rvGenresMovie = view.findViewById<RecyclerView>(R.id.rv_genres_movie)
        rvGenresMovie?.layoutManager=
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        rvGenresMovie?.adapter = MovieGenresAdapter(genres)
    }
}