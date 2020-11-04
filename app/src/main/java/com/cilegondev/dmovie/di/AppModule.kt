package com.cilegondev.dmovie.di

import com.cilegondev.dmovie.core.domain.usecase.DMovieInteractor
import com.cilegondev.dmovie.core.domain.usecase.DMovieUseCase
import com.cilegondev.dmovie.ui.detailmovies.DetailMovieViewModel
import com.cilegondev.dmovie.ui.movie.MovieViewModel
import com.cilegondev.dmovie.ui.tvshow.TVShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<DMovieUseCase> { DMovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TVShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}