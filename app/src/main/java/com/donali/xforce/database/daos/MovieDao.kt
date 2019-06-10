package com.donali.xforce.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.xforce.database.entities.Movie

@Dao
interface MovieDao {

    @Query("select * from movie where imdbID = :id")
    fun getMovieById(id:String):LiveData<Movie>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie:Movie)

    @Query("delete from movie")
    suspend fun deleteAll()
}