package com.donali.xforce.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.donali.xforce.database.RoomDB
import com.donali.xforce.database.repositories.MovieInfoRepository

class MovieInfoViewModel(private val app:Application):AndroidViewModel(app) {
    private val movieInfoRepository:MovieInfoRepository

    init {
       val movieInfoDao = RoomDB.getInstance(app).movieInfoDao()
        movieInfoRepository = MovieInfoRepository(movieInfoDao)
    }

    fun retreiveAllMovieList() = movieInfoRepository.retreiveMovieInfoList()
}