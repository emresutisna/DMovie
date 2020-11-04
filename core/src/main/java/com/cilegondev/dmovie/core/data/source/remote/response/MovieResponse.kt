package com.cilegondev.dmovie.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("id")
    var id: Int =0,

    @SerializedName("title")
    var title: String ="",

    @SerializedName("overview")
    var overview: String ="",

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("vote_average")
    var voteAverage: Float? = 0.0f,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int> = ArrayList(),

    @SerializedName("genres")
    var genres: ArrayList<GenreResponse> = ArrayList()
) : Parcelable