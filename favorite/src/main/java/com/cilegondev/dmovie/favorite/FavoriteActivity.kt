package com.cilegondev.dmovie.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cilegondev.dmovie.core.data.source.local.entity.MovieEntity
import com.cilegondev.dmovie.core.ui.FavoriteAdapter
import com.cilegondev.dmovie.favorite.ui.FavoriteViewModel
import com.cilegondev.dmovie.favorite.ui.favoriteModule
import com.cilegondev.dmovie.ui.detailmovies.DetailMovieActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    lateinit var favoriteAdapter: FavoriteAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        loadKoinModules(favoriteModule)
        itemTouchHelper.attachToRecyclerView(rvFavorites)

        favoriteAdapter = FavoriteAdapter()
        favoriteAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, selectedData)
            intent.putExtra(DetailMovieActivity.EXTRA_TYPE, selectedData.type)
            startActivity(intent)
        }
        viewModel.setFilterType("")
        progress_bar.visibility = View.VISIBLE
        viewModel.getFilterType().observe(this, Observer {
            viewModel.getMovies().observe(this, Observer{ movies ->
                progress_bar.visibility = View.GONE
                favoriteAdapter.setData(movies)
                favoriteAdapter.notifyDataSetChanged()
            })
        })
        toggleFilter.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if(isChecked){
                val type =
                    when(checkedId){
                        btnAll.id ->  ""
                        btnMovie.id -> MovieEntity.TYPE_MOVIE
                        btnTV.id -> MovieEntity.TYPE_TV_SHOW
                        else -> ""
                    }
                viewModel.setFilterType(type)
            }
        }
        with(rvFavorites){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
        fabHome.setOnClickListener{
            super.onBackPressed()
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (rvFavorites != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movie = favoriteAdapter.getSwipedData(swipedPosition)
                movie?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(rvFavorites, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    movie?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })

    override fun onResume() {
        super.onResume()
            viewModel.getFilterType().observe(this, Observer {
                viewModel.getMovies().observe(this, Observer{ movies ->
                    progress_bar.visibility = View.GONE
                    favoriteAdapter.setData(movies)
                    favoriteAdapter.notifyDataSetChanged()
                })
            })
    }
}