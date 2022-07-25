package com.example.movieapp.ui.actors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.genres.GenresAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorsSecreenActivity : AppCompatActivity() {

    private var actors: List<Actor> = emptyList()
    private val actorsRepository = ActorRepository.instance

    private fun getActors(){
        GlobalScope.launch (Dispatchers.IO) {
            actors = actorsRepository.getAllRemoteActors()
            withContext(Dispatchers.Main){
                setupRecyclerView()
            }
        }
//        actors = listOf(
//            Actor(0, " Robert De Niro", false),
//            Actor(0, " Jack Nicholson", false),
//            Actor(0, " Marlon Brando", false),
//            Actor(0, " Katharine Hepburn", false)
//        )
//        setupRecyclerView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_secreen)
        getActors()
    }

    private fun setupRecyclerView(){
        val rvActor = findViewById<RecyclerView>(R.id.rv_actors)
        rvActor.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvActor.adapter = ActorsAdapter(actors)
    }
}