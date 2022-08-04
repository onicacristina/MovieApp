package com.example.movieapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.movieapp.database.conevrtors.GenreConvertor
import com.example.movieapp.database.conevrtors.VideoConvertor
import com.example.movieapp.database.conevrtors.VideoListConvertor
import com.example.movieapp.ui.actors.Actor
import com.example.movieapp.ui.actors.ActorDAO
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.genres.GenreDAO
import com.example.movieapp.ui.movieDetails.MovieDetails
import com.example.movieapp.ui.movieDetails.MovieDetailsDAO
import com.example.movieapp.ui.movieDetails.Video
import com.example.movieapp.ui.movieDetails.VideoListResponse
import com.example.movieapp.ui.movies.Movie
import com.example.movieapp.ui.movies.MovieDAO

class Database private constructor() {
    companion object{
        val instance = Database()
    }


    @androidx.room.Database(
        entities = [Genre::class, Actor::class, Movie::class, MovieDetails::class, Video::class, VideoListResponse::class],
        version = 3
    )

    @TypeConverters(
        value=[ VideoConvertor::class,
        GenreConvertor::class,
        VideoListConvertor::class
        ]
    )

    abstract class MovieAppDatabase: RoomDatabase(){
        abstract fun genresDao(): GenreDAO
        abstract fun actorsDao(): ActorDAO
        abstract fun movieDao(): MovieDAO
        abstract fun movieDetailsDao(): MovieDetailsDAO
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