package com.cilegondev.dmovie.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShowResponse(
    @SerializedName("id")
    var id: Int? = 0,

    @SerializedName("name")
    var name: String? = "",

    @SerializedName("overview")
    var overview: String? = "",

    @SerializedName("poster_path")
    var posterPath: String? = "",

    @SerializedName("vote_average")
    var voteAverage: Float? = 0.0f,

    @SerializedName("first_air_date")
    var firstAirDate: String? = null,


    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int> = ArrayList(),

    @SerializedName("genres")
    var genres:  ArrayList<GenreResponse> = ArrayList()
): Parcelable