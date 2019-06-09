package com.donali.xforce.database.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.donali.xforce.database.RoomDB
import com.donali.xforce.database.entities.MovieInfo
import com.donali.xforce.database.repositories.MovieInfoRepository
import com.donali.xforce.database.services.retrofit.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieInfoViewModel(private val app: Application) : AndroidViewModel(app) {
    private val movieInfoRepository: MovieInfoRepository


    init {
        val movieInfoDao = RoomDB.getInstance(app, viewModelScope).movieInfoDao()
        movieInfoRepository = MovieInfoRepository(movieInfoDao)
    }

    fun retreiveAllMovieList() = viewModelScope.launch(Dispatchers.IO) {
        //        Log.d("CUSTOM","called")


            try{
                //OJO Verificar si hay conexion antes de hacer la llamada
                val response = MovieService.getMovieService().retreiveAllMovies("The Avenger", "movie", 1, MovieService.API_KEY).await()
                if (response.isSuccessful) {

                    with(response) {
                        this.body()?.results?.forEach {
                            insertMovieInfo(it)
                        }
                    }
                } else Log.d("CUSTOM", "shit")
            }
            catch (e:Exception){
                Log.e("CUSTOM",e.toString())
            }
//        }
//        else{
//            isFullLiveData.postValue(true)
//        }

    }


    fun getAllMovieInfoNoLiveData() = movieInfoRepository.getAllMovieInfoNoLiveData()

    fun getAllMovieInfo() = movieInfoRepository.getAllMovieInfo()

    fun insertMovieInfo(movieInfo: MovieInfo) = viewModelScope.launch(Dispatchers.IO) {

        movieInfoRepository.insertMovieInfo(movieInfo)
    }
}