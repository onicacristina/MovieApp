package com.example.movieapp.ui.actors

import com.example.movieapp.network.APIClient

class ActorRepository  private constructor() {
    companion object{
        val instance = ActorRepository()
    }

    private val actorRemoteDataSource = ActorRemoteDataSource(APIClient.instance.retrofit)

    fun getAllRemoteActors() = actorRemoteDataSource.getActors()
}