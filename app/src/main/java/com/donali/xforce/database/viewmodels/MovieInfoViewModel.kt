package com.donali.xforce.database.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.donali.xforce.database.RoomDB
import com.donali.xforce.database.entities.Movie
import com.donali.xforce.database.entities.MovieInfo
import com.donali.xforce.database.repositories.MovieInfoRepository
import com.donali.xforce.database.repositories.MovieRepository
import com.donali.xforce.database.services.retrofit.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieInfoViewModel(private val app: Application) : AndroidViewModel(app) {
    private val movieInfoRepository: MovieInfoRepository
    private val movieRepository: MovieRepository


    init {
        val movieInfoDao = RoomDB.getInstance(app, viewModelScope).movieInfoDao()
        val movieDao = RoomDB.getInstance(app, viewModelScope).movieDao()
        movieInfoRepository = MovieInfoRepository(movieInfoDao)
        movieRepository = MovieRepository(movieDao)
    }

    fun retreiveAllMovieList() = viewModelScope.launch(Dispatchers.IO) {
        //        Log.d("CUSTOM","called")
            try{
                val response = MovieService.getMovieService().retreiveAllMovies("Spider-Man", "movie", 1, MovieService.API_KEY).await()
                if (response.isSuccessful) {

                    with(response) {
                        this.body()?.results?.forEach {
                            insertMovieInfo(it)
                            val movieResponse = MovieService.getMovieService().retreiveMovieById(it.imdbID,MovieService.API_KEY).await()
                            if(movieResponse.isSuccessful){
                                with(movieResponse){
//                                    Log.d("CUSTOM",this.body()!!.Plot)
                                    movieRepository.insertMovie(this.body()!!)
                                }
                            }
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

    fun findMoviesByTitle(title:String) = movieInfoRepository.getMoviesByTitle(title)
    fun retreiveMoviesByTitle(title:String)= viewModelScope.launch(Dispatchers.IO) {
        try {
            /*movieRepository.deleteAllMovies()
            movieInfoRepository.deleteAllMovieInfo()
            val response = MovieService.getMovieService().retreiveAllMovies(title, "movie", 1, MovieService.API_KEY).await()
            if (response.isSuccessful) {
                with(response) {
                    this.body()!!.results.forEach {
                        movieInfoRepository.insertMovieInfo(it)
                        val movieResponse = MovieService.getMovieService().retreiveMovieById(it.imdbID,MovieService.API_KEY).await()
                        if(movieResponse.isSuccessful){
                            with(movieResponse){
                                //                                    Log.d("CUSTOM",this.body()!!.Plot)
                                movieRepository.insertMovie(this.body()!!)
                            }
                        }
                    }
                }
            }*/
        }catch (e:Exception){
            Log.e("CUSTOM",e.toString())

        }
    }


    fun getAllMovieInfoNoLiveData() = movieInfoRepository.getAllMovieInfoNoLiveData()

    fun getAllMovieInfo() = movieInfoRepository.getAllMovieInfo()

    fun getMovieById(id:String) = movieRepository.getMovieById(id)

    fun insertMovieInfo(movieInfo: MovieInfo) = viewModelScope.launch(Dispatchers.IO) {

        movieInfoRepository.insertMovieInfo(movieInfo)
    }

    fun insertMovie(movie:Movie) = viewModelScope.launch(Dispatchers.IO){
        movieRepository.insertMovie(movie)
    }

//    fun deleteAllMovies() = viewModelScope.launch(Dispatchers.IO){
//        movieRepository.deleteAllMovies()
//        movieInfoRepository.deleteAllMovieInfo()
//    }

}