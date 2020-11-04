package com.cilegondev.dmovie.core.data.source.remote.network

import com.cilegondev.dmovie.core.BuildConfig
import com.cilegondev.dmovie.core.data.source.remote.response.ListMovieResponse
import com.cilegondev.dmovie.core.data.source.remote.response.ListTVShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String = BuildConfig.API_KEY): ListMovieResponse

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): ListMovieResponse

    @GET("tv/popular")
    suspend fun getTVShows(@Query("api_key") apiKey: String = BuildConfig.API_KEY): ListTVShowResponse

    @GET("search/tv")
    suspend fun searchTVShows(@Query("query") query: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): ListTVShowResponse
}