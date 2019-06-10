package com.donali.xforce.database.repositories

import androidx.annotation.WorkerThread
import com.donali.xforce.database.daos.MovieDao
import com.donali.xforce.database.entities.Movie

class MovieRepository(private val movieDao: MovieDao) {

    fun getMovieById(id:String) = movieDao.getMovieById(id)

    @WorkerThread
    suspend fun insertMovie(movie:Movie) = movieDao.insert(movie)

    @WorkerThread
    suspend fun deleteAllMovies() = movieDao.deleteAll()
}