package com.donali.xforce.database.services.retrofit

import com.donali.xforce.database.entities.retrofit.MovieResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.omdbapi.com"

interface MovieService {
    @GET("/")
    fun retreiveAllMovies(
        @Query("s")search:String,
        @Query("type")type:String,
        @Query("page")page:Int,
        @Query("apikey")apiKey:String
    ):Deferred<Response<MovieResponse>>

    companion object{
        const val API_KEY = "57eae034"

        fun getMovieService():MovieService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(MovieService::class.java)
    }
}