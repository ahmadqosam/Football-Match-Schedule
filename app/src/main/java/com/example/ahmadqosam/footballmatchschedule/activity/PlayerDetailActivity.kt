package com.example.ahmadqosam.footballmatchschedule.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.api.ApiRepository
import com.example.ahmadqosam.footballmatchschedule.model.Match
import com.example.ahmadqosam.footballmatchschedule.model.Player
import com.example.ahmadqosam.footballmatchschedule.model.Team
import com.example.ahmadqosam.footballmatchschedule.presenter.MainPresenter
import com.example.ahmadqosam.footballmatchschedule.utils.invisible
import com.example.ahmadqosam.footballmatchschedule.utils.visible
import com.example.ahmadqosam.footballmatchschedule.view.MainView
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.id_progress_bar
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.id_swipe_refresh
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.iv_player_thumbnail
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.tv_player_description
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.tv_player_height
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.tv_player_position
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.tv_player_weight
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.setContentView

class PlayerDetailActivity : AppCompatActivity(), MainView {

    private lateinit var playerId: String
    private lateinit var playerName: String
    private lateinit var presenter: MainPresenter

    private lateinit var playerImage: ImageView
    private lateinit var playerPosition: TextView
    private lateinit var playerDescription: TextView
    private lateinit var playerHeight: TextView
    private lateinit var playerWeight: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PlayerDetailsUI().setContentView(this)

        val intent = intent
        playerId = intent.getStringExtra("id")
        playerName = intent.getStringExtra("name")

        playerImage = findViewById(iv_player_thumbnail)
        playerPosition = findViewById(tv_player_position)
        playerDescription = findViewById(tv_player_description)
        playerHeight = findViewById(tv_player_height)
        playerWeight = findViewById(tv_player_weight)
        progressBar = findViewById(id_progress_bar)
        swipeRefresh = findViewById(id_swipe_refresh)

        title = playerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        presenter.getPlayerData(playerId)
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayersData(data: List<Player>) {
        swipeRefresh.isRefreshing = false
        Picasso.get()
            .load(data[0].playerImage)
            .placeholder(R.drawable.ic_default_avatar)
            .error(R.drawable.ic_default_avatar)
            .fit().centerCrop()
            .into(playerImage)
        playerPosition.text = data[0].playerPosition
        playerHeight.text = data[0].playerHeight
        playerWeight.text = data[0].playerWeight
        playerDescription.text = data[0].playerDescription
    }

    override fun showMatchesData(data: List<Match>) {
    }

    override fun showTeamsData(data: List<Team>) {
    }

    override fun showHomeTeamData(data: List<Team>) {
    }

    override fun showAwayTeamData(data: List<Team>) {
    }
}
