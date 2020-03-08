package com.example.ahmadqosam.footballmatchschedule.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ahmadqosam.footballmatchschedule.R.array.league
import com.example.ahmadqosam.footballmatchschedule.R.color.colorAccent
import com.example.ahmadqosam.footballmatchschedule.activity.TeamDetailActivity
import com.example.ahmadqosam.footballmatchschedule.adapter.TeamsAdapter
import com.example.ahmadqosam.footballmatchschedule.api.ApiRepository
import com.example.ahmadqosam.footballmatchschedule.fragment.TeamsFragment.TeamsUiIds.id_spinner
import com.example.ahmadqosam.footballmatchschedule.fragment.TeamsFragment.TeamsUiIds.rv_list_team
import com.example.ahmadqosam.footballmatchschedule.model.Match
import com.example.ahmadqosam.footballmatchschedule.model.Player
import com.example.ahmadqosam.footballmatchschedule.model.Team
import com.example.ahmadqosam.footballmatchschedule.presenter.MainPresenter
import com.example.ahmadqosam.footballmatchschedule.utils.invisible
import com.example.ahmadqosam.footballmatchschedule.utils.parseLeagueNameList
import com.example.ahmadqosam.footballmatchschedule.utils.parseSpace
import com.example.ahmadqosam.footballmatchschedule.utils.visible
import com.example.ahmadqosam.footballmatchschedule.view.MainView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment : Fragment(), AnkoComponent<Context>, MainView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var spinner: Spinner
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = parseLeagueNameList(resources.getStringArray(league).toList())
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeams(parseSpace(leagueName))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        adapter = TeamsAdapter(teams) {
            context?.startActivity<TeamDetailActivity>(
                "id" to "${it.teamId}",
                "name" to "${it.teamName}",
                "badge" to "${it.teamBadge}"
            )
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        swipeRefresh.onRefresh {
            presenter.getTeams(parseSpace(leagueName))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    object TeamsUiIds {
        const val id_spinner = 16
        const val rv_list_team = 17
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = id_spinner
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = rv_list_team
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamsData(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showMatchesData(data: List<Match>) {
    }

    override fun showHomeTeamData(data: List<Team>) {
    }

    override fun showAwayTeamData(data: List<Team>) {
    }

    override fun showPlayersData(data: List<Player>) {
    }
}
