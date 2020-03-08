package com.example.ahmadqosam.footballmatchschedule.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ahmadqosam.footballmatchschedule.R
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {

    private lateinit var currentTab: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search_view.requestFocus()
        search_view.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //do something
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                //do something
                return false
            }

        })

        val intent = intent
        currentTab = intent.getStringExtra("tab")
        when(currentTab){
            getString(R.string.tab_matches) -> {
                search_view.queryHint = getString(R.string.search_match)
//                loadSearchMatchFragment(savedInstanceState)
            }
            getString(R.string.tab_teams) -> {
                search_view.queryHint = getString(R.string.search_team)
//                loadSearchTeamFragment(savedInstanceState)
            }
        }

        back_button.setOnClickListener{
            onBackPressed()
        }

    }




}
