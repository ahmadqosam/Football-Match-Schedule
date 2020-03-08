package com.example.ahmadqosam.footballmatchschedule.view

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.id_progress_bar
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.id_swipe_refresh
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.iv_badge
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.tv_description
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.tv_formed_year
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.tv_stadium
import com.example.ahmadqosam.footballmatchschedule.view.TeamOverviewUI.TeamOverviewIds.tv_team_name
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamOverviewUI<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                backgroundColor = Color.WHITE

                swipeRefreshLayout {
                    id = id_swipe_refresh
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    scrollView {
                        isVerticalScrollBarEnabled = false
                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            linearLayout{
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(10)
                                orientation = LinearLayout.VERTICAL
                                gravity = Gravity.CENTER_HORIZONTAL

                                imageView {
                                    id = iv_badge
                                }.lparams(height = dip(75))

                                textView{
                                    id = tv_team_name
                                    this.gravity = Gravity.CENTER
                                    textSize = 20f
                                    textColor = ContextCompat.getColor(context, R.color.colorAccent)
                                }.lparams{
                                    topMargin = dip(5)
                                }

                                textView{
                                    id = tv_formed_year
                                    this.gravity = Gravity.CENTER
                                }

                                textView{
                                    id = tv_stadium
                                    this.gravity = Gravity.CENTER
                                    textColor = ContextCompat.getColor(context, R.color.colorPrimaryText)
                                }

                                textView{
                                    id = tv_description
                                }.lparams{
                                    topMargin = dip(20)
                                }
                            }
                            progressBar {
                                id = id_progress_bar
                            }.lparams {
                                centerHorizontally()
                            }
                        }
                    }
                }
            }
        }
    }

    object TeamOverviewIds {
        const val id_swipe_refresh = 1
        const val iv_badge = 2
        const val tv_team_name = 3
        const val tv_formed_year = 4
        const val tv_stadium = 5
        const val tv_description = 6
        const val id_progress_bar = 7
    }
}

