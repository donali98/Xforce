package com.donali.xforce.database.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.donali.xforce.database.daos.MovieInfoDao
import com.donali.xforce.database.entities.MovieInfo
import com.donali.xforce.database.services.retrofit.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieInfoRepository(private val movieInfoDao: MovieInfoDao) {

    fun getAllMovieInfo() = movieInfoDao.getAllMovieInfo()
    fun getAllMovieInfoNoLiveData() = movieInfoDao.getAllMovieInfoNoLiveData()

    @WorkerThread
    suspend fun insertMovieInfo(movieInfo: MovieInfo) = movieInfoDao.insertOne(movieInfo)

    @WorkerThread
    suspend fun deleteAllMovieInfo() = movieInfoDao.deleteAll()

}