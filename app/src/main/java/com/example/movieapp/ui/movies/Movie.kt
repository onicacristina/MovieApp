package com.example.movieapp.ui.movies

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "adult")
    var adult: Boolean?,

    @ColumnInfo(name = "poster_path")
    var poster_path: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String?,

    @ColumnInfo(name = "release_date")
    var release_date: String?,

    @ColumnInfo(name = "isSaved")
    var isSaved: Boolean,

    @ColumnInfo(name = "isWatched")
    var isWatched: Boolean
        )
{
    override fun equals(other: Any?) = (other is Movie) && id ==  other.id

    override fun toString(): String {
        return "Movie(id=$id, title='$title')"
    }

}