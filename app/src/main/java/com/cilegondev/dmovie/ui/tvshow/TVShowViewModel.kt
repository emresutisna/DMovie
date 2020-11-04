package com.cilegondev.dmovie.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cilegondev.dmovie.core.domain.usecase.DMovieUseCase

class TVShowViewModel(private val dMovieUseCase: DMovieUseCase) : ViewModel() {

    val tvShows = dMovieUseCase.getTVShows().asLiveData()
}