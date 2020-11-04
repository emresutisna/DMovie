package com.cilegondev.dmovie.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,

    val title: String,

    val overview: String,

    val posterPath: String? = null,

    val voteAverage: Float? = 0.0f,

    val year: String? = null,

    val genres: String? = null,

    val type: String? = null,

    val favorite: Boolean = false
) : Parcelable
{
    companion object{
        const val TYPE_MOVIE: String = "movie"
        const val TYPE_TV_SHOW: String = "tv_show"
    }

}