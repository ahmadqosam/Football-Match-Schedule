package com.example.ahmadqosam.footballmatchschedule.presenter

import com.example.ahmadqosam.footballmatchschedule.TestContextProvider
import com.example.ahmadqosam.footballmatchschedule.api.ApiRepository
import com.example.ahmadqosam.footballmatchschedule.api.TheSportDBApi
import com.example.ahmadqosam.footballmatchschedule.model.*
import com.example.ahmadqosam.footballmatchschedule.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainPresenter
    private val id = "1234"
    private val league = "English%20Premier%20League"
    private val keyword = "test"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getLastMatchList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatches(id)).await(),
                MatchResponse::class.java
            )).thenReturn(response)

            presenter.getLastMatchList(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchesData(matches)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getNextMatchList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatches(id)).await(),
                MatchResponse::class.java
            )).thenReturn(response)

            presenter.getNextMatchList(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchesData(matches)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getMatches() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatches(id)).await(),
                MatchResponse::class.java
            )).thenReturn(response)

            presenter.getMatches(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchesData(matches)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getTeams() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)).await(),
                TeamResponse::class.java
            )).thenReturn(response)

            presenter.getTeams(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamsData(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getTeamData() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeam(id)).await(),
                TeamResponse::class.java
            )).thenReturn(response)

            presenter.getTeamData(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamsData(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getHomeTeamData() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeam(id)).await(),
                TeamResponse::class.java
            )).thenReturn(response)

            presenter.getHomeTeamData(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showHomeTeamData(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getAwayTeamData() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeam(id)).await(),
                TeamResponse::class.java
            )).thenReturn(response)

            presenter.getAwayTeamData(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showAwayTeamData(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getPlayers() {
        val player: MutableList<Player> = mutableListOf()
        val response = PlayersResponse(player)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayers(id)).await(),
                PlayersResponse::class.java
            )).thenReturn(response)

            presenter.getPlayers(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPlayersData(player)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getPlayerData() {
        val players: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(players)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayers(id)).await(),
                PlayerResponse::class.java
            )).thenReturn(response)

            presenter.getPlayers(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPlayersData(players)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getSearchTeamResult() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchTeamResult(keyword)).await(),
                TeamResponse::class.java
            )).thenReturn(response)

            presenter.getPlayers(keyword)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamsData(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getSearchMatchResult() {
        val matches: MutableList<Match> = mutableListOf()
        val response = EventResponse(matches)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchMatchResult(keyword)).await(),
                EventResponse::class.java
            )).thenReturn(response)

            presenter.getPlayers(keyword)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchesData(matches)
            Mockito.verify(view).hideLoading()
        }
    }
}