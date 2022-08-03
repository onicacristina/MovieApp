package com.example.movieapp.ui.onBoardingScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.movieapp.R
import com.example.movieapp.ui.actors.ActorRepository
import com.example.movieapp.ui.actors.ActorsScreenActivity
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.genres.GenreRepository
import com.example.movieapp.ui.genres.GenresScreenActivity
import com.example.movieapp.ui.searchScreen.SearchActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnBoardingScreenActivity : AppCompatActivity() {

    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)

        setClickListeners()

    }

    private fun setClickListeners(){
        val genresButton = findViewById<Button>(R.id.btn_genres)
        val actorsButton = findViewById<Button>(R.id.btn_actors)

        genresButton.setOnClickListener {
            startActivity(Intent(this, GenresScreenActivity::class.java))
        }

        actorsButton.setOnClickListener {
            startActivity(Intent(this, ActorsScreenActivity::class.java))
        }
    }

    private fun verifyIfFilterIsSelected(){
        GlobalScope.launch ( Dispatchers.IO ){
            val  genreCount = genreRepository.getCount()
            val  actorCount = actorRepository.getCount()

            withContext(Dispatchers.Main){
                if(genreCount > 0 && actorCount> 0)
                    SearchActivity.open(this@OnBoardingScreenActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        verifyIfFilterIsSelected()
    }


    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, OnBoardingScreenActivity::class.java))
        }
    }
}