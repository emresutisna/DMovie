package com.cilegondev.dmovie.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @NonNull
    @ColumnInfo(name = "title")
    var title: String,

    @NonNull
    @ColumnInfo(name = "overview")
    var overview: String,

    @NonNull
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Float? = 0.0f,

    @ColumnInfo(name = "year")
    var year: String? = null,

    @ColumnInfo(name = "genres")
    var genres: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
){
    companion object{
        const val TYPE_MOVIE: String = "movie"
        const val TYPE_TV_SHOW: String = "tv_show"
    }

}