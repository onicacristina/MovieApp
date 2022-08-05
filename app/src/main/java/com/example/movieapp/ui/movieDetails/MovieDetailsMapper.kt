package com.example.movieapp.ui.movieDetails

import com.example.movieapp.ui.movies.Movie
import com.example.movieapp.ui.movies.MovieResponse


class MovieDetailsMapper {
    fun map (movieDetailsResponse: MovieDetailsResponse): MovieDetails {
        return MovieDetails(
            id = movieDetailsResponse.id,
            title = movieDetailsResponse.title,
            adult = movieDetailsResponse.adult,
            poster_path = movieDetailsResponse.poster_path,
            overview = movieDetailsResponse.overview,
            backdrop_path = movieDetailsResponse.backdrop_path,
            release_date = movieDetailsResponse.release_date,
            vote_average = movieDetailsResponse.vote_average,
            videos = movieDetailsResponse.videos,
            genres = movieDetailsResponse.genres,
            isSaved = false,
            isWatched = false
        )
    }
}