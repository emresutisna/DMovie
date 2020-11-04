package com.cilegondev.dmovie.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cilegondev.dmovie.core.domain.usecase.DMovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class MovieViewModel(private val dMovieUseCase: DMovieUseCase) : ViewModel() {
    val movies = dMovieUseCase.getMovies().asLiveData()
}