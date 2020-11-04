package com.cilegondev.dmovie.core.data.source.remote

import android.util.Log
import com.cilegondev.dmovie.core.data.source.remote.network.ApiResponse
import com.cilegondev.dmovie.core.data.source.remote.network.ApiService
import com.cilegondev.dmovie.core.data.source.remote.response.MovieResponse
import com.cilegondev.dmovie.core.data.source.remote.response.TVShowResponse
import com.cilegondev.dmovie.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchMovies(query: String): Flow<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.searchMovies(query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTVShows(): Flow<ApiResponse<List<TVShowResponse>>> {
        EspressoIdlingResource.increment()

        return flow {
            try {
                val response = apiService.getTVShows()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchTVShows(query: String): Flow<ApiResponse<List<TVShowResponse>>> {
        EspressoIdlingResource.increment()

        return flow {
            try {
                val response = apiService.searchTVShows(query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }
}