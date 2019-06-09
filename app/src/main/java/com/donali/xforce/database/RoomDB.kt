package com.donali.xforce.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.donali.xforce.database.daos.MovieInfoDao
import com.donali.xforce.database.entities.MovieInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [MovieInfo::class],version = 2,exportSchema = false)
abstract class RoomDB :RoomDatabase(){
    abstract fun movieInfoDao():MovieInfoDao
    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context,scope: CoroutineScope): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context, RoomDB::class.java, "MovieDB")
                    .fallbackToDestructiveMigration()
//                    .addCallback(RoomDBCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private class RoomDBCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDB(database.movieInfoDao())
                    }
                }
            }

            suspend fun populateDB(movieInfoDao: MovieInfoDao) {
                movieInfoDao.deleteAll()
            }
        }
    }
}