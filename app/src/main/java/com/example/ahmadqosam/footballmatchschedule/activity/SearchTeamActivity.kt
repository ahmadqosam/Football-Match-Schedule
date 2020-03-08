package com.example.ahmadqosam.footballmatchschedule.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.adapter.TeamsAdapter
import com.example.ahmadqosam.footballmatchschedule.api.ApiRepository
import com.example.ahmadqosam.footballmatchschedule.model.Match
import com.example.ahmadqosam.footballmatchschedule.model.Player
import com.example.ahmadqosam.footballmatchschedule.model.Team
import com.example.ahmadqosam.footballmatchschedule.presenter.MainPresenter
import com.example.ahmadqosam.footballmatchschedule.utils.invisible
import com.example.ahmadqosam.footballmatchschedule.utils.parseSpace
import com.example.ahmadqosam.footballmatchschedule.utils.visible
import com.example.ahmadqosam.footballmatchschedule.view.MainView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.startActivity

class SearchTeamActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: TeamsAdapter
    private var teams: MutableList<Team> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        adapter = TeamsAdapter(teams) {
            startActivity<TeamDetailActivity>(
                "id" to "${it.teamId}",
                "name" to "${it.teamName}",
                "badge" to "${it.teamBadge}"
            )
        }
        list_team.adapter = adapter
        list_team.layoutManager = LinearLayoutManager(applicationContext)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        search_view.setIconifiedByDefault(false)
        search_view.requestFocus()
        search_view.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearchTeamResult(parseSpace(query.orEmpty()))
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.getSearchTeamResult(parseSpace(query.orEmpty()))
                return false
            }

        })

        back_button.setOnClickListener{
            onBackPressed()
        }


    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun showTeamsData(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }


    override fun showMatchesData(data: List<Match>) {
    }

    override fun showPlayersData(data: List<Player>) {
    }

    override fun showHomeTeamData(data: List<Team>) {
    }

    override fun showAwayTeamData(data: List<Team>) {
    }

}
