package com.cilegondev.dmovie.core.domain.usecase

import com.cilegondev.dmovie.core.data.Resource
import com.cilegondev.dmovie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DMovieUseCase {
    fun getMovies(): Flow<Resource<List<Movie>>>

    fun getTVShows(): Flow<Resource<List<Movie>>>

    fun searchMovies(query: String): Flow<Resource<List<Movie>>>

    fun searchTVShows(query: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getFavoriteMoviesByType(type: String): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}