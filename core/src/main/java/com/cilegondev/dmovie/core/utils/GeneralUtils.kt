package com.cilegondev.dmovie.core.utils

object GeneralUtils {
    const val BASE_SMALL_IMAGE_URL = "https://image.tmdb.org/t/p/w138_and_h175_face/"
    const val BASE_ORIGINAL_IMAGE_URL = "https://image.tmdb.org/t/p/original/"

    private fun getGenres(): HashMap<Int, String>{
        val genres = HashMap<Int, String>()
        genres[28] = "Action"
        genres[12] = "Adventure"
        genres[16] = "Animation"
        genres[35] = "Comedy"
        genres[80] = "Crime"
        genres[99] = "Documentary"
        genres[18] = "Drama"
        genres[10751] = "Family"
        genres[14] = "Fantasy"
        genres[36] = "History"
        genres[27] = "Horror"
        genres[10402] = "Music"
        genres[9648] = "Mystery"
        genres[10749] = "Romance"
        genres[878] = "Science Fiction"
        genres[10770] = "TV Movie"
        genres[53] = "Thriller"
        genres[10752] = "War"
        genres[37] = "Western"
        genres[10759] = "Action & Adventure"
        genres[10762] = "Kids"
        genres[10763] = "News"
        genres[10764] = "Reality"
        genres[10765] = "Sci-Fi & Fantasy"
        genres[10766] = "Soap"
        genres[10767] = "Talk"
        genres[10768] = "War & Politics"
        return genres
    }

    fun getGenreByIds(idGenres: ArrayList<Int>): String{
        val dataGenres: java.util.HashMap<Int, String> = getGenres()
        val stringBuilder = StringBuilder()
        for ((idx, id) in idGenres.withIndex()) {
            val valueGenre = dataGenres[id]
            stringBuilder.append(valueGenre)
            if (idx != idGenres.size - 1) stringBuilder.append(", ")
        }
        return stringBuilder.toString()
    }

}