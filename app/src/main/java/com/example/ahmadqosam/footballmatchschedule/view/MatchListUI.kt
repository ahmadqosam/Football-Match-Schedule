package com.example.ahmadqosam.footballmatchschedule.view

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.adapter.MatchesAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchListUI<T>(private val matchesAdapter: MatchesAdapter) : AnkoComponent<T> {

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = MatchIds.id_spinner
            }

            swipeRefresh = swipeRefreshLayout {

                id = MatchIds.swipe_refresh

                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)
                    gravity = Gravity.CENTER

                    listTeam = recyclerView {
                        id = MatchIds.list_match
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                        adapter = matchesAdapter
                    }

                    progressBar = progressBar {
                        id = MatchIds.progress_bar
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    object MatchIds {
        const val progress_bar = 1
        const val swipe_refresh = 2
        const val id_spinner = 11
        const val list_match = 99
    }
}