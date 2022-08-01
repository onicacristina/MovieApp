package com.example.movieapp.ui.actors

import androidx.room.*

@Dao
interface ActorDAO {

    @Query("SELECT * FROM actors")
    fun getAll(): List<Actor>

    @Insert
    fun save(actor: Actor)

    @Insert
    fun saveAll(actors: List<Actor>)

    @Delete
    fun delete(actor: Actor)

    @Delete
    fun deleteAll(actors: List<Actor>)

    @Query("DELETE FROM actors")
    fun deleteAll()

    @Transaction
    fun replaceAll(actors: List<Actor>){
        deleteAll()
        saveAll(actors)
    }

    @Query("SELECT COUNT(id) FROM actors")
    fun getCount(): Int

    @Query("SELECT id FROM actors")
    fun getAllIds() : List<Int>

}