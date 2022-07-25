package com.example.movieapp.ui.actors

class ActorMapper {
    fun map (actorResponse: ActorResponse) : Actor{
        return Actor(
            id = actorResponse.id,
            name = actorResponse.name,
            photo = actorResponse.photo,
            isSelected = false
        )
    }
}