package com.example.movieapp.ui.searchScreen.ui.searchMovies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSearchMoviesBinding
import com.example.movieapp.ui.movies.Movie
import com.example.movieapp.ui.movies.MovieAdapter
import com.example.movieapp.ui.movies.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchMoviesFragment : Fragment(R.layout.fragment_search_movies) {


    private var movies: List<Movie> = emptyList()
    private val movieRepository = MovieRepository.instance


//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(SearchMoviesViewModel::class.java)
//
//        val root = inflater.inflate(inflater, container, false)
//        val root: View = binding.root
//
////        val textView: TextView = binding.textHome
////        homeViewModel.text.observe(viewLifecycleOwner) {
////            textView.text = it
////        }
//
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        getMovies(view)
    }

    private fun getMovies(view: View){
        GlobalScope.launch (Dispatchers.IO) {
            movies = movieRepository.getALLRemoteMovies()
           withContext(Dispatchers.Main){
               val rvMovies = view?.findViewById<RecyclerView>(R.id.rv_movies)
               rvMovies?.layoutManager =
                   LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)
               rvMovies?.adapter = MovieAdapter(movies)

           }
        }

    }



}