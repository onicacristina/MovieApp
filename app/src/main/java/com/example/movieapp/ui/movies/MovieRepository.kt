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

    fun getAllLocalMovies() = movieLocalDataSource.getAll()
    fun saveLocal(movie: Movie) = movieLocalDataSource.save(movie)
    fun saveAllLocal(movies: List<Movie>) = movieLocalDataSource.saveAll(movies)
    fun deleteLocal(movie: Movie) = movieLocalDataSource.delete(movie)
    fun deleteAllLocal() = movieLocalDataSource.deleteAll()
    fun deleteAllLocal(movies: List<Movie>) = movieLocalDataSource.deleteAll(movies)
    fun replaceAllLocal(movies: List<Movie>) = movieLocalDataSource.replaceAll(movies)
    fun getCount() = movieLocalDataSource.getCount()
    fun getSavedMovies() = movieLocalDataSource.getSavedMovies()
    fun getWatchedMovies() = movieLocalDataSource.getWatchedMovies()
    fun insertOrReplace(movie: Movie) = movieLocalDataSource.insertOrUpdate(movie)

}