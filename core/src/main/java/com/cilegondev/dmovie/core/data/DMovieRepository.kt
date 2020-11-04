package com.cilegondev.dmovie.core.data

import com.cilegondev.dmovie.core.data.source.local.LocalDataSource
import com.cilegondev.dmovie.core.data.source.local.entity.MovieEntity
import com.cilegondev.dmovie.core.data.source.remote.RemoteDataSource
import com.cilegondev.dmovie.core.data.source.remote.network.ApiResponse
import com.cilegondev.dmovie.core.data.source.remote.response.MovieResponse
import com.cilegondev.dmovie.core.data.source.remote.response.TVShowResponse
import com.cilegondev.dmovie.core.domain.model.Movie
import com.cilegondev.dmovie.core.domain.repository.IMovieRepository
import com.cilegondev.dmovie.core.utils.AppExecutors
import com.cilegondev.dmovie.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DMovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun getMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            public override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies(Movie.TYPE_MOVIE).map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            public override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            public override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)

            }
        }.asFlow()

    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            public override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.searchMovies(Movie.TYPE_MOVIE, query).map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                    data == null || data.isEmpty()

            public override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                    remoteDataSource.searchMovies(query)

            public override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()


    override fun getTVShows(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<TVShowResponse>>() {
            public override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies(MovieEntity.TYPE_TV_SHOW).map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            public override suspend fun createCall(): Flow<ApiResponse<List<TVShowResponse>>> =
                remoteDataSource.getTVShows()

            public override suspend fun saveCallResult(data: List<TVShowResponse>) {
                val tvShowList = DataMapper.mapTVShowResponsesToEntities(data)
                localDataSource.insertMovies(tvShowList)

            }
        }.asFlow()

    override fun searchTVShows(query: String): Flow<Resource<List<Movie>>> =
            object : NetworkBoundResource<List<Movie>, List<TVShowResponse>>() {
                public override fun loadFromDB(): Flow<List<Movie>> {
                    return localDataSource.searchMovies(MovieEntity.TYPE_TV_SHOW, query).map {
                        DataMapper.mapMovieEntitiesToDomain(it)
                    }
                }

                override fun shouldFetch(data: List<Movie>?): Boolean =
                        data == null || data.isEmpty()

                public override suspend fun createCall(): Flow<ApiResponse<List<TVShowResponse>>> =
                        remoteDataSource.searchTVShows(query)

                public override suspend fun saveCallResult(data: List<TVShowResponse>) {
                    val tvShowList = DataMapper.mapTVShowResponsesToEntities(data)
                    localDataSource.insertMovies(tvShowList)

                }
            }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }


    override fun getFavoriteMoviesByType(type: String): Flow<List<Movie>> {
        return localDataSource.getFavoriteMoviesByType(type).map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToMovieEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }

    }
}