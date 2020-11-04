package com.cilegondev.dmovie.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cilegondev.dmovie.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class],
    version = 1,
    exportSchema = false)
abstract class DMovieDatabase : RoomDatabase() {
    abstract fun dmovieDao() : DMovieDao

}