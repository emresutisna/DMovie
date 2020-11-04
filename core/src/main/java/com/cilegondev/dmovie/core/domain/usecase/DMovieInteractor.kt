package com.cilegondev.dmovie.core.domain.usecase

import com.cilegondev.dmovie.core.data.Resource
import com.cilegondev.dmovie.core.domain.model.Movie
import com.cilegondev.dmovie.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class DMovieInteractor(private val dmovieRepository: IMovieRepository) : DMovieUseCase {
    override fun getMovies(): Flow<Resource<List<Movie>>> = dmovieRepository.getMovies()

    override fun getTVShows(): Flow<Resource<List<Movie>>> = dmovieRepository.getTVShows()

    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> = dmovieRepository.searchMovies(query)

    override fun searchTVShows(query: String): Flow<Resource<List<Movie>>> = dmovieRepository.searchTVShows(query)

    override fun getFavoriteMovies(): Flow<List<Movie>> = dmovieRepository.getFavoriteMovies()

    override fun getFavoriteMoviesByType(type: String): Flow<List<Movie>> = dmovieRepository.getFavoriteMoviesByType(type)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = dmovieRepository.setFavoriteMovie(movie, state)
}