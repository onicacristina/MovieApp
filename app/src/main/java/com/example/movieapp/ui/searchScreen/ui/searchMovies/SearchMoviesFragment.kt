package com.example.movieapp.ui.searchScreen.ui.searchMovies


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSearchMoviesBinding
import com.example.movieapp.ui.actors.ActorRepository
import com.example.movieapp.ui.genres.GenreRepository
import com.example.movieapp.ui.genres.GenresAdapter
import com.example.movieapp.ui.movies.Movie
import com.example.movieapp.ui.movies.MovieAdapter
import com.example.movieapp.ui.movies.MovieRepository
import com.example.movieapp.ui.onBoardingScreen.OnBoardingScreenActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchMoviesFragment : Fragment() {

    private var _binding: FragmentSearchMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView
    private val binding get() = _binding!!

    private var movies: List<Movie> = emptyList()
    private val movieRepository = MovieRepository.instance
    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorRepository.instance

    private var genreIds = ""
    private var actorIds = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val SearchMoviesViewModel =
            ViewModelProvider(this).get(SearchMoviesViewModel::class.java)

        _binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        getParams()
        getSearchedMovieQuery()
        setOnClickListeners()

    }

    private fun getSearchedMovieQuery(){
        val search = binding.svSearch
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length!! >= 1) {
                    getSearchedMovies(newText)
                } else
                    getMovies()
                return false
            }
        })
    }

    private fun getParams(){
        GlobalScope.launch (Dispatchers.IO) {
            val savedGenresIds: List<Int> = genreRepository.getAllLocalIds()
            genreIds = savedGenresIds.joinToString(separator = "|") { "$it" }
            val savedActorsIds: List<Int> = actorRepository.getAllLocalIds()
            actorIds = savedActorsIds.joinToString(separator = "|") { "$it" }

            withContext(Dispatchers.Main) {
                getMovies()
            }
        }
    }

    private fun getMovies(){
        GlobalScope.launch (Dispatchers.IO) {
            movies = movieRepository.getAllRemoteMovies(actorIds, genreIds)
            withContext(Dispatchers.Main){
               moviesLoaded()
           }
        }
    }

    private fun getSearchedMovies(query : String){
        GlobalScope.launch (Dispatchers.IO) {
            movies = movieRepository.getAllSearchedMovies(query)
            withContext(Dispatchers.Main){
                moviesLoaded()
            }
        }
    }

    private fun moviesLoaded(){
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val rvMovies = binding.rvMovies
        rvMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies.adapter = MovieAdapter(movies)
    }

    private fun setOnClickListeners() {
        val btnFilter = binding.btnFilter
        btnFilter.setOnClickListener {
            val intent = Intent(activity,OnBoardingScreenActivity::class.java)
            startActivity(intent)
        }


    }

}