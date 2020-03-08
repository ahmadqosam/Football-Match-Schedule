package com.example.ahmadqosam.footballmatchschedule.view

import com.example.ahmadqosam.footballmatchschedule.model.Match
import com.example.ahmadqosam.footballmatchschedule.model.Player
import com.example.ahmadqosam.footballmatchschedule.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchesData(data: List<Match>)
    fun showTeamsData(data: List<Team>)
    fun showPlayersData(data: List<Player>)
    fun showHomeTeamData(data: List<Team>)
    fun showAwayTeamData(data: List<Team>)
}