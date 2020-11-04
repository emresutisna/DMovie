package com.cilegondev.dmovie.core.data.source.local.room

import androidx.room.*
import com.cilegondev.dmovie.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DMovieDao {

    @Query("SELECT * FROM movies where type=:type")
    fun getMovies(type: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where type=:type and title like '%'+ :query + '%'")
    fun searchMovies(type: String, query: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where favorite=1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where favorite=1 and type=:type")
    fun getFavoriteMoviesByType(type: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where id = :id")
    fun getItem(id: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieEntities: List<MovieEntity>)

    @Update
    fun updateMovie(movieEntity: MovieEntity)


}