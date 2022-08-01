package com.example.movieapp.ui.movies

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient


class MovieRepository private constructor() {
    companion object{
        val instance = MovieRepository()
    }

    private val movieRemoteDataSource = MovieRemoteDataSource(APIClient.instance.retrofit)
    private val movieLocalDataSource = MovieLocalDataSource(Database.instance)

    fun getAllRemoteMovies(withCast: String, withGenres: String): List<Movie>  {
        var movies: List<Movie> = movieRemoteDataSource.getMovies(withCast, withGenres)
        return movies
    }
    fun getAllSearchedMovies(query: String): List<Movie> {
        var searchedMovie: List<Movie> = movieRemoteDataSource.getSearchedMovie(query)
        return searchedMovie
    }

    fun getAllLocalActors() = movieLocalDataSource.getAll()
    fun saveLocal(actor: Movie) = movieLocalDataSource.save(actor)
    fun saveAllLocal(actors: List<Movie>) = movieLocalDataSource.saveAll(actors)
    fun deleteLocal(actor: Movie) = movieLocalDataSource.delete(actor)
    fun deleteAllLocal() = movieLocalDataSource.deleteAll()
    fun deleteAllLocal(actors: List<Movie>) = movieLocalDataSource.deleteAll(actors)
    fun replaceAllLocal(actors: List<Movie>) = movieLocalDataSource.replaceAll(actors)
    fun getCount() = movieLocalDataSource.getCount()

}