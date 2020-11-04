package com.cilegondev.dmovie.core.data.source.local

import com.cilegondev.dmovie.core.data.source.local.entity.MovieEntity
import com.cilegondev.dmovie.core.data.source.local.room.DMovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val mDMovieDao: DMovieDao) {

    fun getMovies(type: String): Flow<List<MovieEntity>> = mDMovieDao.getMovies(type)

    fun searchMovies(type: String, query: String): Flow<List<MovieEntity>> = mDMovieDao.searchMovies(type, query)

    fun setFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.favorite = newState
        mDMovieDao.updateMovie(movieEntity)
    }

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = mDMovieDao.getFavoriteMovies()

    fun getFavoriteMoviesByType(type: String): Flow<List<MovieEntity>> = mDMovieDao.getFavoriteMoviesByType(type)

    suspend fun insertMovies(movieEntities: List<MovieEntity>) = mDMovieDao.insertMovies(movieEntities)
}