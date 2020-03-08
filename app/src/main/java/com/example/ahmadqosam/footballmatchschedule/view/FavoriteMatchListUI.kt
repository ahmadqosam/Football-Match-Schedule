package com.example.ahmadqosam.footballmatchschedule.view

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.adapter.FavoriteMatchAdapter
import com.example.ahmadqosam.footballmatchschedule.view.FavoriteMatchListUI.Ids.favorite_match_list
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchListUI<T>(private val mainAdapter: FavoriteMatchAdapter) : AnkoComponent<T> {

    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout


    override fun createView(ui: AnkoContext<T>) = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout {

                id = Ids.swipe_refresh

                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = favorite_match_list
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                        adapter = mainAdapter
                    }
                }
            }
        }
    }

    object Ids {
        const val swipe_refresh = 20
        const val favorite_match_list = 21
    }
}