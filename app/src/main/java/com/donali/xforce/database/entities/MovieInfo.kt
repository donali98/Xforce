package com.donali.xforce.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_info")
data class MovieInfo(
    @ColumnInfo(name = "title")
    val Title: String,
    @ColumnInfo(name = "year")
    val Year: String,
    @ColumnInfo(name = "imdbID")
    val imdbID: String,
    @ColumnInfo(name = "type")
    val Type: String,
    @ColumnInfo(name = "poster")
    val Poster: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}