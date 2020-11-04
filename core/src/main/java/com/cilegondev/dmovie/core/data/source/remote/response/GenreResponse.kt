package com.cilegondev.dmovie.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreResponse(
    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String
): Parcelable