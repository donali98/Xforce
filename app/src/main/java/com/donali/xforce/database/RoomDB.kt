package com.donali.xforce.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.donali.xforce.database.daos.MovieInfoDao
import com.donali.xforce.database.entities.MovieInfo

@Database(entities = [MovieInfo::class],version = 2,exportSchema = false)
abstract class RoomDB :RoomDatabase(){
    abstract fun movieInfoDao():MovieInfoDao
    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context, RoomDB::class.java, "MovieDB")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}