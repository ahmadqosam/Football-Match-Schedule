package com.example.ahmadqosam.footballmatchschedule.db

data class FavoriteMatch(
    val id: Long?,
    val matchId: String?,
    val date: String?,
    val homeTeamId: String?,
    val awayTeamId: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val homeBadge: String?,
    val awayBadge: String?,
    val homeScore: String?,
    val awayScore: String?,
    val homeShots: String?,
    val awayShots: String?,
    val homeScorer: String?,
    val awayScorer: String?,
    val homeYellowCards: String?,
    val awayYellowCards: String?,
    val homeRedCards: String?,
    val awayRedCards: String?
) {
    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val MATCH_ID : String = "MATCH_ID"
        const val DATE: String = "DATE_"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_BADGE: String = "HOME_BADGE"
        const val AWAY_BADGE: String = "AWAY_BADGE"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val HOME_SCORER: String = "HOME_SCORER"
        const val AWAY_SCORER: String = "AWAY_SCORER"
        const val HOME_YELLOW_CARDS: String = "HOME_YELLOW_CARDS"
        const val AWAY_YELLOW_CARDS: String = "AWAY_YELLOW_CARDS"
        const val HOME_RED_CARDS: String = "HOME_RED_CARDS"
        const val AWAY_RED_CARDS: String = "AWAY_RED_CARDS"
    }
}