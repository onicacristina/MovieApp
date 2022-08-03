package com.example.movieapp.ui.movies

import androidx.room.*
import com.example.movieapp.ui.actors.Actor

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>


    @Update
    fun save(movie: Movie)

    @Insert
    fun saveAll(movies: List<Movie>)

    @Delete
    fun delete(movies: Movie)

    @Delete
    fun deleteAll(movies: List<Movie>)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Transaction
    fun replaceAll(movies: List<Movie>){
        deleteAll()
        saveAll(movies)
    }

    @Query("SELECT COUNT(id) FROM movies")
    fun getCount(): Int

    @Query("SELECT * FROM movies WHERE isSaved==1")
    fun getSavedMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE isWatched==1")
    fun getWatchedMovies(): List<Movie>

    @Transaction
    fun insertOrUpdateMovie(movie: Movie) {
        insertChooseCategories(movie)
        updateChooseCategories(movie)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChooseCategories(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChooseCategories(movie: Movie)
}