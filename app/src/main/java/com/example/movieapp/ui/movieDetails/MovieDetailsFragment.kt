package com.example.movieapp.ui.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.ui.actors.ActorRepository

class MovieDetailsFragment : Fragment() {

    private val movieDetailsRepository = MovieDetailsRepository.instance
    private lateinit var viewModel: DetailsViewModel

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
            val tvTitle = view.findViewById<TextView>(R.id.tv_movie_title)

            tvTitle.text = it.title
        }

        }
    }



}