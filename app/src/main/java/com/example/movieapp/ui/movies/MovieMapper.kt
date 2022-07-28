package com.example.movieapp.ui.movies


class MovieMapper {
    fun map (movieResponse: MovieResponse): Movie {
        return Movie(
            id = movieResponse.id,
            title = movieResponse.title,
            adult = movieResponse.adult,
            poster_path = movieResponse.poster_path,
            overview = movieResponse.overview,
            backdrop_path = movieResponse.backdrop_path,
            release_date = movieResponse.release_date
        )
    }
}