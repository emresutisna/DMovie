package com.cilegondev.dmovie.ui.detailmovies

import androidx.lifecycle.ViewModel
import com.cilegondev.dmovie.core.domain.model.Movie
import com.cilegondev.dmovie.core.domain.usecase.DMovieUseCase

class DetailMovieViewModel(private val dMovieUseCase: DMovieUseCase) : ViewModel() {
    fun setFavorite(movie: Movie, favoriteState: Boolean) {
        dMovieUseCase.setFavoriteMovie(movie, favoriteState)
    }
}