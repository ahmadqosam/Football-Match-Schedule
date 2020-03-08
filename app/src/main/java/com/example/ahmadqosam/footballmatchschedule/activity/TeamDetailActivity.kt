package com.example.ahmadqosam.footballmatchschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.example.ahmadqosam.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.example.ahmadqosam.footballmatchschedule.R.id.add_to_favorite
import com.example.ahmadqosam.footballmatchschedule.R.menu.detail_menu
import com.example.ahmadqosam.footballmatchschedule.adapter.TeamDetailPagerAdapter
import com.example.ahmadqosam.footballmatchschedule.db.FavoriteTeam
import com.example.ahmadqosam.footballmatchschedule.db.database
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar


class TeamDetailActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var id: String
    private lateinit var name: String
    private lateinit var badge: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_team_detail)

        val intent = intent
        id = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        badge = intent.getStringExtra("badge")

        viewpager_team_detail.adapter =
                TeamDetailPagerAdapter(supportFragmentManager, id)
        tabs_team_detail.setupWithViewPager(viewpager_team_detail)

        title = name
        supportActionBar?.elevation = 0F
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteState()
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
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
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to id,
                    FavoriteTeam.TEAM_NAME to name,
                    FavoriteTeam.TEAM_BADGE to badge)
            }
            container_team_detail.snackbar(getString(R.string.favorite_success)).show()
        } catch (e: SQLiteConstraintException){
            container_team_detail.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to id)
            }
            container_team_detail.snackbar(getString(R.string.unfavorite_success)).show()
        } catch (e: SQLiteConstraintException){
            container_team_detail.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

}
