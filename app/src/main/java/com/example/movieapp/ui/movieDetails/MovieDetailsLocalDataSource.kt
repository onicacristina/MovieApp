package com.example.movieapp.ui.movieDetails

import com.example.movieapp.database.Database

class MovieDetailsLocalDataSource(database: Database) {
    private var movieDetailsDAO: MovieDetailsDAO = database.movieAppDatabase.movieDetailsDao()

    fun getAll() = movieDetailsDAO.getAll()
    fun save(movie: MovieDetails) = movieDetailsDAO.save(movie)
    fun saveAll(movies: List<MovieDetails>) = movieDetailsDAO.saveAll(movies)
    fun delete(movie: MovieDetails) = movieDetailsDAO.delete(movie)
    fun deleteAll() = movieDetailsDAO.deleteAll()
    fun deleteAll(movies: List<MovieDetails>) = movieDetailsDAO.deleteAll(movies)
    fun replaceAll(movies: List<MovieDetails>) = movieDetailsDAO.replaceAll(movies)
    fun getCount() = movieDetailsDAO.getCount()

}