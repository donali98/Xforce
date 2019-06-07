package com.donali.xforce.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.donali.xforce.database.entities.MovieInfo

@Dao
interface MovieInfoDao {

    @Query("select * from movie_info")
    fun getAllMovieInfo():LiveData<List<MovieInfo>>
}