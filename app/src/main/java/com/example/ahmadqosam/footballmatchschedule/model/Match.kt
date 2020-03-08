package com.example.ahmadqosam.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class Match (
    @SerializedName("strHomeTeam")
    val homeTeam: String?,

    @SerializedName("strAwayTeam")
    val awayTeam: String?,

    @SerializedName("strDate")
    val date: String?,

    @SerializedName("intHomeScore")
    val homeScore: String?,

    @SerializedName("intAwayScore")
    val awayScore: String?,

    @SerializedName("strHomeGoalDetails")
    val homeGoalDetails: String,

    @SerializedName("strAwayGoalDetails")
    val awayGoalDetails: String,

    @SerializedName("intHomeShots")
    val homeShots: String?,

    @SerializedName("intAwayShots")
    val awayShots: String?,

    @SerializedName("strHomeYellowCards")
    val homeYellowCards: String,

    @SerializedName("strAwayYellowCards")
    val awayYellowCards: String,

    @SerializedName("strHomeRedCards")
    val homeRedCards: String,

    @SerializedName("strAwayRedCards")
    val awayRedCards: String,

    @SerializedName("idHomeTeam")
    val homeTeamId: String?,

    @SerializedName("idAwayTeam")
    val awayTeamId: String?,

    @SerializedName("idEvent")
    val matchId: String?
)
