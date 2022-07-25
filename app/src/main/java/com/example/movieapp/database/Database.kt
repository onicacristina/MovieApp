package com.example.movieapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.genres.GenreDAO

class Database private constructor() {
    companion object{
        val instance = Database()
    }

    @androidx.room.Database(
        entities = [Genre::class],
        version = 0
    )

    abstract class MovieAppDatabase: RoomDatabase(){
        abstract fun genresDao(): GenreDAO
    }

    lateinit var movieAppDatabase: MovieAppDatabase
        private set

    fun initialise(context: Context){
        this.movieAppDatabase = Room.databaseBuilder(
            context,
            MovieAppDatabase::class.java,
            "movie_app.db"
        ).fallbackToDestructiveMigration().build()
    }
}