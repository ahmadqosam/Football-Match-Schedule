package com.example.ahmadqosam.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.ahmadqosam.footballmatchschedule.api.ApiRepository
import com.example.ahmadqosam.footballmatchschedule.model.Match
import com.example.ahmadqosam.footballmatchschedule.model.Player
import com.example.ahmadqosam.footballmatchschedule.model.Team
import com.example.ahmadqosam.footballmatchschedule.presenter.MainPresenter
import com.example.ahmadqosam.footballmatchschedule.utils.invisible
import com.example.ahmadqosam.footballmatchschedule.utils.visible
import com.example.ahmadqosam.footballmatchschedule.view.MainView
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.id_progress_bar
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.id_swipe_refresh
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.iv_badge
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.tv_description
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.tv_formed_year
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.tv_stadium
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.tv_team_name
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.onRefresh


class TeamOverviewFragment : Fragment(), MainView {

    companion object {
        fun newInstance(id: String): TeamOverviewFragment{
            val fragment = TeamOverviewFragment()
            val args = Bundle()
            args.putString("id", id)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var presenter: MainPresenter
    private lateinit var teams: Team
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView

    private var teamId: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        if(arguments != null){
            teamId = arguments?.getString("id", "")
        }

        val view =  TeamOverviewUI<Fragment>()
            .createView(AnkoContext.create(requireContext(), this))

        progressBar = view.findViewById(id_progress_bar)
        swipeRefresh = view.findViewById(id_swipe_refresh)
        teamBadge = view.findViewById(iv_badge)
        teamName = view.findViewById(tv_team_name)
        teamDescription = view.findViewById(tv_description)
        teamFormedYear = view.findViewById(tv_formed_year)
        teamStadium = view.findViewById(tv_stadium)

        swipeRefresh.onRefresh {
            if(teamId != null){
                val id = teamId as String
                presenter.getTeamData(id)
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        if(teamId != null){
            val id = teamId as String
            presenter.getTeamData(id)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchesData(data: List<Match>) {
    }

    override fun showTeamsData(data: List<Team>) {
        teams = Team(data[0].teamId,
            data[0].teamName,
            data[0].teamBadge)
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        teamDescription.text = data[0].teamDescription
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium    }

    override fun showHomeTeamData(data: List<Team>) {
    }

    override fun showAwayTeamData(data: List<Team>) {
    }

    override fun showPlayersData(data: List<Player>) {
    }
}
