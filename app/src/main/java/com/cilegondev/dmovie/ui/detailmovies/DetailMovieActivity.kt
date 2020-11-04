package com.cilegondev.dmovie.ui.detailmovies

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cilegondev.dmovie.R
import com.cilegondev.dmovie.core.domain.model.Movie
import com.cilegondev.dmovie.core.utils.GeneralUtils
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    private val viewModel: DetailMovieViewModel by viewModel()

    companion object{
        const val  EXTRA_MOVIE = "extra_movie"
        const val  EXTRA_TYPE = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE) as Movie
        showDetailMovie(movie)
        setFavoriteState(movie.favorite)
    }

    private fun showDetailMovie(movie: Movie){
        tvTitle.text = movie.title
        tvReleaseYear.text = movie.year
        Glide.with(this)
            .load(GeneralUtils.BASE_ORIGINAL_IMAGE_URL + movie.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
            )
            .into(imgPoster)
        tvGenres.text = movie.genres
        tvOverview.text = movie.overview
        rating.rating = movie.voteAverage as Float / 2
        supportActionBar?.title = movie.title

        var statusFavorite = movie.favorite
        fabFavorite.setOnClickListener {
            statusFavorite = !statusFavorite
            viewModel.setFavorite(movie, statusFavorite)
            setFavoriteState(statusFavorite)
        }
    }

    private fun setFavoriteState(state: Boolean){
        if (state) {
            fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
