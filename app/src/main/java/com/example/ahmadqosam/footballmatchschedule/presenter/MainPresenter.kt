package com.example.ahmadqosam.footballmatchschedule.presenter

import com.example.ahmadqosam.footballmatchschedule.api.ApiRepository
import com.example.ahmadqosam.footballmatchschedule.api.TheSportDBApi
import com.example.ahmadqosam.footballmatchschedule.model.*
import com.example.ahmadqosam.footballmatchschedule.utils.CoroutineContextProvider
import com.example.ahmadqosam.footballmatchschedule.utils.isNotEmptyOrNull
import com.example.ahmadqosam.footballmatchschedule.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLastMatchList(leagueId: String) {
        if(isNotEmptyOrNull(leagueId)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLastMatches(leagueId)).await(),
                    MatchResponse::class.java)
                if(data.events != null){
                    view.showMatchesData(data.events)
                }
                view.hideLoading()
            }
        }
    }

    fun getNextMatchList(leagueId: String) {
        if(isNotEmptyOrNull(leagueId)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatches(leagueId)).await(),
                    MatchResponse::class.java)
                if(data.events != null){
                    view.showMatchesData(data.events)
                }
                view.hideLoading()
            }
        }
    }

    fun getMatches(eventId: String){
        if(isNotEmptyOrNull(eventId)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getMatches(eventId)).await(),
                    MatchResponse::class.java)
                if(data.events != null){
                    view.showMatchesData(data.events)
                }
                view.hideLoading()
            }
        }
    }

    fun getTeams(league: String){
        if(isNotEmptyOrNull(league)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)).await(),
                    TeamResponse::class.java)
                if(data.teams != null){
                    view.showTeamsData(data.teams)
                }
                view.hideLoading()
            }
        }
    }

    fun getTeamData(teamId: String){
        if(isNotEmptyOrNull(teamId)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamId)).await(),
                    TeamResponse::class.java)
                if(data.teams != null){
                    view.showTeamsData(data.teams)
                }
                view.hideLoading()
            }
        }
    }

    fun getHomeTeamData(teamId: String){
        if(isNotEmptyOrNull(teamId)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamId)).await(),
                    TeamResponse::class.java)
                if(data.teams != null){
                    view.showHomeTeamData(data.teams)
                }
                view.hideLoading()
            }
        }
    }

    fun getAwayTeamData(teamId: String){
        if(isNotEmptyOrNull(teamId)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamId)).await(),
                    TeamResponse::class.java)
                if(data.teams != null){
                    view.showAwayTeamData(data.teams)
                }
                view.hideLoading()
            }
        }
    }

    fun getPlayers(teamId: String){
        if(isNotEmptyOrNull(teamId)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPlayers(teamId)).await(),
                    PlayersResponse::class.java)
                if(data.player != null){
                    view.showPlayersData(data.player)
                }
                view.hideLoading()
            }
        }
    }

    fun getPlayerData(playerId: String){
        if(isNotEmptyOrNull(playerId)) {
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPlayerData(playerId)).await(),
                    PlayerResponse::class.java)
                if(data.players != null){
                    view.showPlayersData(data.players)
                }
                view.hideLoading()
            }
        }
    }

    fun getSearchTeamResult(keyword: String){
        if(isNotEmptyOrNull(keyword)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getSearchTeamResult(keyword)).await(),
                    TeamResponse::class.java)
                if(data.teams != null){
                    view.showTeamsData(data.teams)
                }
                view.hideLoading()
            }
        }
    }

    fun getSearchMatchResult(keyword: String){
        if(isNotEmptyOrNull(keyword)){
            view.showLoading()
            GlobalScope.launch(context.main){
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getSearchMatchResult(keyword)).await(),
                    EventResponse::class.java)
                if(data.event != null){
                    view.showMatchesData(data.event)
                }
                view.hideLoading()
            }
        }
    }
}