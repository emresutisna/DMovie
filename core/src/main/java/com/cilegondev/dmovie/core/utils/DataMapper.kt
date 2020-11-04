package com.cilegondev.dmovie.core.utils

import com.cilegondev.dmovie.core.data.source.local.entity.MovieEntity
import com.cilegondev.dmovie.core.data.source.remote.response.MovieResponse
import com.cilegondev.dmovie.core.data.source.remote.response.TVShowResponse
import com.cilegondev.dmovie.core.domain.model.Movie

object DataMapper {
    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        posterPath = it.posterPath,
                        voteAverage = it.voteAverage,
                        year = it.year,
                        genres = it.genres,
                        type = it.type,
                        favorite = it.favorite
                )
            }

    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val genres = GeneralUtils.getGenreByIds(it.genreIds)
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                year = it.releaseDate?.substring(0,4),
                genres = genres,
                type = Movie.TYPE_MOVIE
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTVShowResponsesToEntities(input: List<TVShowResponse>): List<MovieEntity> {
        val tvShowList = ArrayList<MovieEntity>()
        input.map {
            val genres = GeneralUtils.getGenreByIds(it.genreIds)
            val tvShow = MovieEntity(
                id = it.id as Int,
                title = it.name as String,
                overview = it.overview as String,
                posterPath = it.posterPath as String,
                voteAverage = it.voteAverage,
                year = it.firstAirDate?.substring(0,4),
                genres = genres,
                type = Movie.TYPE_TV_SHOW
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapDomainToMovieEntity(input: Movie) = MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            year = input.year,
            genres = input.genres,
            type = input.type,
            favorite = input.favorite
    )

    fun mapMovieEntityToDomain(input: MovieEntity) = Movie(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            year = input.year,
            genres = input.genres,
            type = input.type,
            favorite = input.favorite
    )
}