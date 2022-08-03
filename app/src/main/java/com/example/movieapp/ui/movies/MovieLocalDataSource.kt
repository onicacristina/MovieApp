package com.example.movieapp.ui.movies

import com.example.movieapp.database.Database

class MovieLocalDataSource(database: Database) {
    private var movieDAO: MovieDAO = database.movieAppDatabase.movieDao()

    fun getAll() = movieDAO.getAll()
    fun save(movie: Movie) = movieDAO.save(movie)
    fun saveAll(movies: List<Movie>) = movieDAO.saveAll(movies)
    fun delete(movie: Movie) = movieDAO.delete(movie)
    fun deleteAll() = movieDAO.deleteAll()
    fun deleteAll(movies: List<Movie>) = movieDAO.deleteAll(movies)
    fun replaceAll(movies: List<Movie>) = movieDAO.replaceAll(movies)
    fun getCount() = movieDAO.getCount()
    fun getSavedMovies() = movieDAO.getSavedMovies()
    fun getWatchedMovies() = movieDAO.getWatchedMovies()
    fun insertOrUpdate(movie: Movie) = movieDAO.insertOrUpdateMovie(movie)

}