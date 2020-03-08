package com.example.ahmadqosam.footballmatchschedule.api

import com.example.ahmadqosam.footballmatchschedule.BuildConfig

object TheSportDBApi {

    fun getLastMatches(id: String?): String {
        if(id == null || id == ""){
            // set English Premier League as default League
            return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=4328"
        }
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=$id"
    }

    fun getNextMatches(id: String?): String {
        if(id == null || id == ""){
            // set English Premier League as default League
            return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=4328"
        }
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=$id"
    }

    fun getMatches(matchId: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=$matchId"
    }

    fun getTeams(league: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_teams.php?l=$league"
    }

    fun getTeam(teamId: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=$teamId"
    }

    fun getPlayers(teamId: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php?id=$teamId"
    }

    fun getPlayerData(playerId: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupplayer.php?id=$playerId"
    }

    fun getSearchTeamResult(keyword: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?t=$keyword"
    }

    fun getSearchMatchResult(keyword: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e=$keyword"
    }
}