package com.cilegondev.dmovie.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cilegondev.dmovie.R
import com.cilegondev.dmovie.core.data.Resource
import com.cilegondev.dmovie.core.ui.TVShowAdapter
import com.cilegondev.dmovie.ui.detailmovies.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_t_v_show.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment() {
    private val viewModel: TVShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t_v_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val tvShowAdapter = TVShowAdapter()
            tvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, selectedData)
                intent.putExtra(DetailMovieActivity.EXTRA_TYPE, selectedData.type)
                startActivity(intent)
            }
            progress_bar.visibility = View.VISIBLE
            viewModel.tvShows.observe(viewLifecycleOwner, Observer{ resource ->
                if (resource != null) {
                    when (resource) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar.visibility = View.GONE
                            tvShowAdapter.setData(resource.data)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })
            with(rvTVShows){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}
