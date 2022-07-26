package com.example.movieapp.ui.genres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.onBoardingScreen.OnBoardingScreenActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenresScreenActivity : AppCompatActivity() {

    private val genresRepository = GenreRepository.instance
    private var genres: List<Genre> = emptyList()

    private fun getGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            genres = genresRepository.getAllRemoteGenres()
            withContext(Dispatchers.Main) {
                preselectSaveGenres()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres_screen)

        setOnClickListeners()
        getGenres()
    }

    private fun setupRecyclerView() {
        val rvGenre = findViewById<RecyclerView>(R.id.rv_genres)
        rvGenre.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGenre.adapter = GenresAdapter(genres)
    }

    private fun setOnClickListeners() {
        val btnSave = findViewById<FloatingActionButton>(R.id.btnSave)
        btnSave.setOnClickListener {
            saveGenres()
        }
    }

    private fun saveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            //genresRepository.replaceAllLocal(getSelectedGenres())
            genresRepository.deleteAllLocal()
            genresRepository.saveAllLocal(getSelectedGenres())
        }
        OnBoardingScreenActivity.open(this)
    }

    private fun getSelectedGenres(): List<Genre> {
        return genres.filter { genres -> genres.isSelected }
    }

    private fun preselectSaveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val saveGenre: List<Genre> = genresRepository.getAllLocalGenres()
            withContext(Dispatchers.Main) {
                genres.forEach { genre ->
                    genre.isSelected = saveGenre.contains(genre)
                }
                setupRecyclerView()
            }
        }
    }
}