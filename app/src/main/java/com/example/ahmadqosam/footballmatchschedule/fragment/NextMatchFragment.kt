package com.example.ahmadqosam.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.activity.MatchDetailActivity
import com.example.ahmadqosam.footballmatchschedule.adapter.MatchesAdapter
import com.example.ahmadqosam.footballmatchschedule.api.ApiRepository
import com.example.ahmadqosam.footballmatchschedule.model.Match
import com.example.ahmadqosam.footballmatchschedule.model.Player
import com.example.ahmadqosam.footballmatchschedule.model.Team
import com.example.ahmadqosam.footballmatchschedule.presenter.MainPresenter
import com.example.ahmadqosam.footballmatchschedule.utils.invisible
import com.example.ahmadqosam.footballmatchschedule.utils.parseLeagueNameList
import com.example.ahmadqosam.footballmatchschedule.utils.retrieveLeagueId
import com.example.ahmadqosam.footballmatchschedule.utils.visible
import com.example.ahmadqosam.footballmatchschedule.view.MainView
import com.example.ahmadqosam.footballmatchschedule.view.MatchListUI
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class NextMatchFragment : Fragment(), MainView {

    private var matchList: MutableList<Match> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var presenter: MainPresenter
    private lateinit var matchesAdapter: MatchesAdapter
    private lateinit var spinner: Spinner
    // set English Premier League as default League
    private var leagueId = "4328"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        matchesAdapter = MatchesAdapter(matchList) {
            startActivity<MatchDetailActivity>(
                "matchId" to "${it.matchId}",
                "homeTeamId" to "${it.homeTeamId}",
                "awayTeamId" to "${it.awayTeamId}"
            )
        }
        val view =  MatchListUI<Fragment>(matchesAdapter)
            .createView(AnkoContext.create(requireContext(), this))

        spinner = view.findViewById(MatchListUI.MatchIds.id_spinner)
        progressBar = view.findViewById(MatchListUI.MatchIds.progress_bar)
        swipeRefresh = view.findViewById(MatchListUI.MatchIds.swipe_refresh)

        val spinnerItems = parseLeagueNameList(resources.getStringArray(R.array.league).toList())
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueId = retrieveLeagueId(spinner.selectedItem.toString(), resources.getStringArray(R.array.league).toList())
                presenter.getNextMatchList(leagueId)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getNextMatchList(leagueId)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.getNextMatchList(leagueId)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchesData(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matchList.clear()
        matchList.addAll(data)
        matchesAdapter.notifyDataSetChanged()
    }

    override fun showHomeTeamData(data: List<Team>) {
    }

    override fun showAwayTeamData(data: List<Team>) {
    }

    override fun showTeamsData(data: List<Team>) {
    }

    override fun showPlayersData(data: List<Player>) {
    }
}
