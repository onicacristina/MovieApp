package com.example.movieapp.ui.genres

import androidx.room.*

@Dao
interface GenreDAO {

    @Query("SELECT * FROM genres")
    fun getAll():List<Genre>

    @Insert
    fun save(genre: Genre)

    @Insert
    fun saveAll(genres: List<Genre>)

    @Delete
    fun delete(genre: Genre)

    @Delete
    fun deleteAll(genres: List<Genre>)

    @Query("DELETE FROM genres")
    fun deleteAll()

    @Transaction
    fun replaceAll(genres: List<Genre>){
        deleteAll()
        saveAll(genres)
    }

    @Query("SELECT COUNT(id) FROM genres")
    fun getCount(): Int

    @Query("SELECT id FROM genres")
    fun getAllIds() : List<Int>
}