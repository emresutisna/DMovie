package com.cilegondev.dmovie.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cilegondev.dmovie.core.domain.model.Movie
import com.cilegondev.dmovie.core.domain.usecase.DMovieUseCase

class FavoriteViewModel(private val dMovieUseCase: DMovieUseCase) : ViewModel() {
    private val filterType : MutableLiveData<String> =  MutableLiveData()

    fun getMovies(): LiveData<List<Movie>> {
        return if(filterType.value as String != ""){
            dMovieUseCase.getFavoriteMoviesByType(filterType.value as String).asLiveData()
        }else{
            dMovieUseCase.getFavoriteMovies().asLiveData()
        }
    }

    fun setFilterType(type: String){
        this.filterType.postValue(type)
    }

    fun getFilterType(): LiveData<String>{
        return filterType
    }

    fun setFavorite(movie: Movie) {
        val newState = !movie.favorite
        dMovieUseCase.setFavoriteMovie(movie, newState)
    }
}