package com.cilegondev.dmovie.core.di

import androidx.room.Room
import com.cilegondev.dmovie.core.BuildConfig
import com.cilegondev.dmovie.core.data.DMovieRepository
import com.cilegondev.dmovie.core.data.source.local.LocalDataSource
import com.cilegondev.dmovie.core.data.source.local.room.DMovieDatabase
import com.cilegondev.dmovie.core.data.source.remote.RemoteDataSource
import com.cilegondev.dmovie.core.data.source.remote.network.ApiService
import com.cilegondev.dmovie.core.domain.repository.IMovieRepository
import com.cilegondev.dmovie.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<DMovieDatabase>().dmovieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DMovieDatabase::class.java, "DMovie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { DMovieRepository(get(), get(), get()) }
}
