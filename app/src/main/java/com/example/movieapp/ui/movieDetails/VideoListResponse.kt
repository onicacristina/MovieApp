package com.example.movieapp.ui.movieDetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.ui.movies.MovieResponse
import com.google.gson.annotations.SerializedName

@Entity(tableName = "videoResponse")
class VideoListResponse (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("results")
    val movies: List<Video>
){
}