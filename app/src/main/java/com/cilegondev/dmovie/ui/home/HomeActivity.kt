package com.cilegondev.dmovie.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cilegondev.dmovie.R
import com.cilegondev.dmovie.ui.movie.MovieFragment
import com.cilegondev.dmovie.ui.tvshow.TVShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(MovieFragment())
        nav_main.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.movie_menu -> return loadFragment(MovieFragment())
                R.id.tv_menu -> return loadFragment(TVShowFragment())
                R.id.favorite_menu -> {
                    val uri = Uri.parse("dmovie://favorite")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    return true
                }
                else -> return true
            }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_layout, fragment)
                .commit()
            return true
        }
        return false
    }
}
