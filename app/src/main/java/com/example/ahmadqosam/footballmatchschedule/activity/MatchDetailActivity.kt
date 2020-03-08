package com.example.ahmadqosam.footballmatchschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.example.ahmadqosam.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.example.ahmadqosam.footballmatchschedule.R.id.add_to_favorite
import com.example.ahmadqosam.footballmatchschedule.R.menu.detail_menu
import com.example.ahmadqosam.footballmatchschedule.api.ApiRepository
import com.example.ahmadqosam.footballmatchschedule.db.FavoriteMatch
import com.example.ahmadqosam.footballmatchschedule.db.database
import com.example.ahmadqosam.footballmatchschedule.model.Match
import com.example.ahmadqosam.footballmatchschedule.model.Player
import com.example.ahmadqosam.footballmatchschedule.model.Team
import com.example.ahmadqosam.footballmatchschedule.presenter.MainPresenter
import com.example.ahmadqosam.footballmatchschedule.utils.invisible
import com.example.ahmadqosam.footballmatchschedule.utils.isNotEmptyOrNull
import com.example.ahmadqosam.footballmatchschedule.utils.visible
import com.example.ahmadqosam.footballmatchschedule.view.MainView
import com.example.ahmadqosam.footballmatchschedule.view.MatchDetailsUI
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.setContentView


class MatchDetailActivity : AppCompatActivity(), MainView {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var presenter: MainPresenter
    private lateinit var match: Match
    private lateinit var homeTeam: Team
    private lateinit var awayTeam: Team
    private lateinit var matchId: String
    private lateinit var homeTeamId: String
    private lateinit var awayTeamId: String

    private lateinit var tvDate: TextView
    private lateinit var tvHomeTeam: TextView
    private lateinit var tvAwayTeam: TextView
    private lateinit var tvHomeScore: TextView
    private lateinit var tvAwayScore: TextView
    private lateinit var tvHomeGoalDetails: TextView
    private lateinit var tvAwayGoalDetails: TextView
    private lateinit var tvHomeShots: TextView
    private lateinit var tvAwayShots: TextView
    private lateinit var tvHomeYellowCards: TextView
    private lateinit var tvAwayYellowCards: TextView
    private lateinit var tvHomeRedCards: TextView
    private lateinit var tvAwayRedCards: TextView
    private lateinit var ivHomeBadge: ImageView
    private lateinit var ivAwayBadge: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MatchDetailsUI(applicationContext).setContentView(this)

        val intent = intent
        matchId = intent.getStringExtra("matchId")
        homeTeamId = intent.getStringExtra("homeTeamId")
        awayTeamId = intent.getStringExtra("awayTeamId")

        tvDate = findViewById(MatchDetailsUI.Ids.tv_date)
        tvHomeTeam = findViewById(MatchDetailsUI.Ids.tv_home_team)
        tvAwayTeam = findViewById(MatchDetailsUI.Ids.tv_away_team)
        tvHomeScore = findViewById(MatchDetailsUI.Ids.tv_home_score)
        tvAwayScore = findViewById(MatchDetailsUI.Ids.tv_away_score)
        tvHomeGoalDetails = findViewById(MatchDetailsUI.Ids.tv_home_scorer)
        tvAwayGoalDetails = findViewById(MatchDetailsUI.Ids.tv_away_scorer)
        tvHomeShots = findViewById(MatchDetailsUI.Ids.tv_home_shots)
        tvAwayShots = findViewById(MatchDetailsUI.Ids.tv_away_shots)
        tvHomeYellowCards = findViewById(MatchDetailsUI.Ids.tv_home_yellow_cards)
        tvAwayYellowCards = findViewById(MatchDetailsUI.Ids.tv_away_yellow_cards)
        tvHomeRedCards = findViewById(MatchDetailsUI.Ids.tv_home_red_cards)
        tvAwayRedCards = findViewById(MatchDetailsUI.Ids.tv_away_red_cards)
        ivHomeBadge = findViewById(MatchDetailsUI.Ids.home_team_badge)
        ivAwayBadge = findViewById(MatchDetailsUI.Ids.away_team_badge)
        swipeRefresh = findViewById(MatchDetailsUI.Ids.swipe_refresh_layout)
        progressBar = findViewById(MatchDetailsUI.Ids.progress_bar)

        title = "Match Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        presenter.getMatches(matchId)
        presenter.getHomeTeamData(homeTeamId)
        presenter.getAwayTeamData(awayTeamId)

        favoriteState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE_MATCH,
                    FavoriteMatch.MATCH_ID to match.matchId,
                    FavoriteMatch.DATE to match.date,
                    FavoriteMatch.HOME_TEAM_ID to homeTeam.teamId,
                    FavoriteMatch.AWAY_TEAM_ID to awayTeam.teamId,
                    FavoriteMatch.HOME_TEAM_NAME to homeTeam.teamName,
                    FavoriteMatch.AWAY_TEAM_NAME to awayTeam.teamName,
                    FavoriteMatch.HOME_BADGE to homeTeam.teamBadge,
                    FavoriteMatch.AWAY_BADGE to awayTeam.teamBadge,
                    FavoriteMatch.HOME_SCORE to match.homeScore,
                    FavoriteMatch.AWAY_SCORE to match.awayScore,
                    FavoriteMatch.HOME_SCORER to match.homeGoalDetails,
                    FavoriteMatch.AWAY_SCORER to match.awayGoalDetails,
                    FavoriteMatch.HOME_SHOTS to match.homeShots,
                    FavoriteMatch.AWAY_SHOTS to match.awayShots,
                    FavoriteMatch.HOME_YELLOW_CARDS to match.homeYellowCards,
                    FavoriteMatch.AWAY_YELLOW_CARDS to match.awayYellowCards,
                    FavoriteMatch.HOME_RED_CARDS to match.homeRedCards,
                    FavoriteMatch.AWAY_RED_CARDS to match.awayRedCards
                )
            }
            swipeRefresh.snackbar(getString(R.string.favorite_success)).show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(MATCH_ID = {id})",
                    "id" to matchId)
            }
            swipeRefresh.snackbar(getString(R.string.unfavorite_success)).show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                .whereArgs("(MATCH_ID = {id})",
                    "id" to matchId)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setMatchScore(data: List<Match>){
        if(isNotEmptyOrNull(data[0].homeScore)){
            tvHomeScore.text = data[0].homeScore
        } else {
            tvHomeScore.visibility = View.INVISIBLE
        }

        if(isNotEmptyOrNull(data[0].awayScore)){
            tvAwayScore.text = data[0].awayScore
        } else {
            tvAwayScore.visibility = View.INVISIBLE
        }
    }

    private fun parseShots(shots: String?): String?{
        if(isNotEmptyOrNull(shots)){
            return shots
        }
        return "-"
    }

    private fun parseGoalDetails(goalDetails: String): String{
        if(isNotEmptyOrNull(goalDetails)){
            return goalDetails.replace(';','\n',true)
        }
        return "-"
    }

    private fun parseCardsCount(cardDetails: String): String{
        if(isNotEmptyOrNull(cardDetails)){
            return cardDetails.split(";").size.toString()
        }
        return "-"
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchesData(data: List<Match>) {
        match = data[0]

        swipeRefresh.isRefreshing = false

        tvDate.text = data[0].date

        tvHomeTeam.text = data[0].homeTeam
        tvAwayTeam.text = data[0].awayTeam

        tvHomeGoalDetails.text = parseGoalDetails(data[0].homeGoalDetails)
        tvAwayGoalDetails.text = parseGoalDetails(data[0].awayGoalDetails)

        tvHomeShots.text = parseShots(data[0].homeShots)
        tvAwayShots.text = parseShots(data[0].awayShots)

        tvHomeYellowCards.text = parseCardsCount(data[0].homeYellowCards)
        tvAwayYellowCards.text = parseCardsCount(data[0].awayYellowCards)

        tvHomeRedCards.text = parseCardsCount(data[0].homeRedCards)
        tvAwayRedCards.text = parseCardsCount(data[0].awayRedCards)

        setMatchScore(data)
    }

    override fun showHomeTeamData(data: List<Team>) {
        homeTeam = Team(data[0].teamId, data[0].teamName, data[0].teamBadge)
        Picasso.get().load(data[0].teamBadge).into(ivHomeBadge)
    }

    override fun showAwayTeamData(data: List<Team>) {
        awayTeam = Team(data[0].teamId, data[0].teamName, data[0].teamBadge)
        Picasso.get().load(data[0].teamBadge).into(ivAwayBadge)
    }

    override fun showTeamsData(data: List<Team>) {
    }

    override fun showPlayersData(data: List<Player>) {
    }
}
