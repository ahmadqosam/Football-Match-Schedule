package com.example.ahmadqosam.footballmatchschedule.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.R.id.*
import com.example.ahmadqosam.footballmatchschedule.R.layout.activity_main
import com.example.ahmadqosam.footballmatchschedule.fragment.FavoritesFragment
import com.example.ahmadqosam.footballmatchschedule.fragment.MatchesFragment
import com.example.ahmadqosam.footballmatchschedule.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    private lateinit var currentTab: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation = 0F
        setContentView(activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                matches ->{
                    menuItem?.getItem(0)?.setVisible(true)
                    loadMatchesFragment(savedInstanceState)
                    currentTab = getString(R.string.tab_matches)
                }
                teams -> {
                    menuItem?.getItem(0)?.setVisible(true)
                    loadTeamsFragment(savedInstanceState)
                    currentTab = getString(R.string.tab_teams)
                }
                favorites -> {
                    menuItem?.getItem(0)?.setVisible(false)
                    loadFavoritesFragment(savedInstanceState)
                    currentTab = getString(R.string.tab_favorites)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = matches
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        menuItem = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.action_search){
            if(currentTab == getString(R.string.tab_matches)){
                startActivity<SearchMatchActivity>()
            } else if(currentTab == getString(R.string.tab_teams)){
                startActivity<SearchTeamActivity>()
            }
        }
        return false
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MatchesFragment(), MatchesFragment::class.java.simpleName)
                .commit()
        }
    }


    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                .commit()
        }
    }
}
