package com.example.movieapp.ui.actors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.genres.GenresAdapter
import com.example.movieapp.ui.onBoardingScreen.OnBoardingScreenActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
                preselectSaveActors()
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

        setOnClickListeners()
        getActors()
    }

    private fun setupRecyclerView(){
        val rvActor = findViewById<RecyclerView>(R.id.rv_actors)
        rvActor.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvActor.adapter = ActorsAdapter(actors)
    }

    private fun setOnClickListeners() {
        val btnSave = findViewById<FloatingActionButton>(R.id.btnSave)
        btnSave.setOnClickListener {
            saveActors()
        }
    }

    private fun saveActors() {
        GlobalScope.launch (Dispatchers.IO ){
            actorsRepository.deleteAllLocal()
            actorsRepository.saveAllLocal(getSelectedActors())
        }
        OnBoardingScreenActivity.open(this)
    }

    private fun getSelectedActors(): List<Actor> {
        return actors.filter { actors -> actors.isSelected }
    }

    private fun preselectSaveActors() {
        GlobalScope.launch (Dispatchers.IO){
            val saveActor: List<Actor> = actorsRepository.getAllLocalActors()
            withContext(Dispatchers.Main) {
                actors.forEach { actor ->
                    actor.isSelected = saveActor.contains(actor)
                }
                setupRecyclerView()
            }
        }
    }
}