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
import com.example.movieapp.databinding.FragmentMovieDetailsBinding
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.utils.Constants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToLong


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsRepository = MovieDetailsRepository.instance
    private lateinit var viewModel: DetailsViewModel
    private var genres : List<Genre> = emptyList()
    private var videos : List<Video> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel= ViewModelProvider(requireActivity())[DetailsViewModel::class.java]
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]
        val model = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]
        model.getCurrentMovie().observe(viewLifecycleOwner){ it?.let {
            val id: Int = it.id
            getMovie(id)
        }
        }
    }

    private fun getMovie(id: Int){
        GlobalScope.launch (Dispatchers.IO) {
            var movie = movieDetailsRepository.getMovieDetails(id)
            withContext(Dispatchers.Main){
                genres = movie.genres
                binding.tvMovieTitle.text = movie.title
                binding.tvReleaseDate.text = movie.release_date
                binding.tvVoteAverage.text = movie.vote_average?.let { roundOffDecimal(it.toDouble()) }
                binding.tvMovieDescription.text = movie.overview
                if(movie.poster_path != null)
                    Glide.with(binding.ivMoviePhoto.context).load(Constants.IMAGE_URL_MOVIE + movie.poster_path).into(binding.ivMoviePhoto)

                loadYtbVideos(movie)
                setupRecyclerView()
            }
        }
    }

    private fun roundOffDecimal(number: Double): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number)
    }

    private fun setupRecyclerView(){
        val rvGenresMovie = binding.rvGenresMovie
        rvGenresMovie.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvGenresMovie.adapter = MovieGenresAdapter(genres)
    }

    private fun loadYtbVideos(movie: MovieDetails){
        binding.ytbVideo.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                if (movie.videos?.movies?.size != 0)
                movie.videos?.movies?.get(0)?.let { youTubePlayer.loadVideo(findYoutubeTrailer(movie), 0f) }
            }
        })
    }

    private fun findYoutubeTrailer(movie: MovieDetails) : String {
        movie?.videos?.movies?.let{ videoList ->
            for(video in videoList) {
                if(video.type == "Trailer")
                    return video.key
            }
        }
        return ""
    }
}