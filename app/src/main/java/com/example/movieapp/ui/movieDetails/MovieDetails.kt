package com.example.movieapp.ui.movieDetails

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.movies.Movie


@Entity(tableName = "moviesDetails")
data class MovieDetails (
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

    var videos: VideoListResponse?,

    var genres: List<Genre>,

    @ColumnInfo(name = "isSaved")
    var isSaved: Boolean,

    @ColumnInfo(name = "isWatched")
    var isWatched: Boolean
) {
    override fun equals(other: Any?) = (other is Movie) && id == other.id

    override fun toString(): String {
        return "Movie(id=$id, title='$title')"
    }
}
