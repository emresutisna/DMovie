package com.cilegondev.dmovie.core.ui

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cilegondev.dmovie.core.R
import com.cilegondev.dmovie.core.domain.model.Movie
import com.cilegondev.dmovie.core.utils.EspressoIdlingResource
import com.cilegondev.dmovie.core.utils.GeneralUtils
import kotlinx.android.synthetic.main.item_movie_row.view.*


class TVShowAdapter: RecyclerView.Adapter<TVShowAdapter.ViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null
    override fun getItemCount(): Int = listData.size

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = listData[position]
        holder.bind(show)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(show: Movie) {
            EspressoIdlingResource.increment()
            with(itemView) {
                tvGenres.text = show.genres
                val title = show.title
                val releaseYear = show.year as String
                val titleSpan = SpannableString(title)
                val releaseYearSpan = SpannableString(releaseYear)

                titleSpan.setSpan(AbsoluteSizeSpan(resources.getDimensionPixelSize(R.dimen.title_text_size)),0, title.length, SPAN_INCLUSIVE_INCLUSIVE)
                releaseYearSpan.setSpan(AbsoluteSizeSpan(resources.getDimensionPixelSize(R.dimen.release_year_text_size)),0, releaseYear.length, SPAN_INCLUSIVE_INCLUSIVE)
                releaseYearSpan.setSpan(StyleSpan(Typeface.ITALIC), 0, releaseYear.length, SPAN_INCLUSIVE_INCLUSIVE)
                val titleText = TextUtils.concat(titleSpan, " - ", releaseYearSpan)
                tvTitle.text = titleText
                rating.rating = show.voteAverage as Float / 2
                Glide.with(itemView.context)
                    .load(GeneralUtils.BASE_SMALL_IMAGE_URL + show.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
            if(!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) EspressoIdlingResource.decrement()
        }
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}