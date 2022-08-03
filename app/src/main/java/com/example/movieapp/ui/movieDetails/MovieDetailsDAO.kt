package com.example.movieapp.ui.movieDetails

import androidx.room.*
import com.example.movieapp.ui.movies.Movie

@Dao
interface MovieDetailsDAO {
    @Query("SELECT * FROM moviesDetails")
    fun getAll(): List<MovieDetails>

    @Insert
    fun save(movie: MovieDetails)

    @Insert
    fun saveAll(movies: List<MovieDetails>)

    @Delete
    fun delete(movies: MovieDetails)

    @Delete
    fun deleteAll(movies: List<MovieDetails>)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Transaction
    fun replaceAll(movies: List<MovieDetails>){
        deleteAll()
        saveAll(movies)
    }

    @Query("SELECT COUNT(id) FROM moviesDetails")
    fun getCount(): Int
}