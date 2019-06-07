package com.donali.xforce.database.repositories

import android.util.Log
import com.donali.xforce.database.daos.MovieInfoDao
import com.donali.xforce.database.services.retrofit.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieInfoRepository(private val movieInfoDao: MovieInfoDao) {

    fun retreiveMovieInfoList() = GlobalScope.launch (Dispatchers.IO){
        val response = MovieService.getMovieService().retreiveAllMovies("The Avenger","movie",1,MovieService.API_KEY).await()
        if(response.isSuccessful){

            with(response){
                this.body()?.results?.forEach {
                    Log.d("CUSTOM",it.Title)
                }
            }
        }
        else Log.d("CUSTOM","shit")
    }
}