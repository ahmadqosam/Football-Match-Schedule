package com.example.ahmadqosam.footballmatchschedule.view

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.activity.PlayerDetailActivity
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.id_player_attributes
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.id_progress_bar
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.id_swipe_refresh
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.iv_player_thumbnail
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.tv_player_description
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.tv_player_height
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.tv_player_position
import com.example.ahmadqosam.footballmatchschedule.view.PlayerDetailsUI.PlayerDetailsIds.tv_player_weight
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PlayerDetailsUI: AnkoComponent<PlayerDetailActivity> {
    override fun createView(ui: AnkoContext<PlayerDetailActivity>): View {
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

                            imageView {
                                id = iv_player_thumbnail
                                scaleType = ImageView.ScaleType.FIT_CENTER
                                adjustViewBounds = true
                            }.lparams(width = matchParent, height = wrapContent){
                                bottomMargin= dip(20)
                            }

                            textView{
                                id = tv_player_position
                                this.gravity = Gravity.CENTER
                                textSize = 20f
                                textColor = ContextCompat.getColor(context, R.color.colorAccent)
                            }.lparams(width = matchParent, height = wrapContent){
                                below(iv_player_thumbnail)
                                bottomMargin= dip(10)
                            }

                            linearLayout {
                                id = id_player_attributes
                                orientation = LinearLayout.HORIZONTAL

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    textView(context.getString(R.string.player_height)){
                                        this.gravity = Gravity.CENTER
                                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                                        textSize = 14f
                                    }.lparams(width = matchParent, height = wrapContent)

                                    textView{
                                        id = tv_player_height
                                        this.gravity = Gravity.CENTER
                                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                                        textColor = ContextCompat.getColor(context, R.color.colorAccent)
                                    }.lparams(width = matchParent, height = wrapContent){
                                        margin = dip(5)
                                    }

                                }.lparams(width = dip(0), height = wrapContent) {
                                    weight = 1F
                                }

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    textView(context.getString(R.string.player_weight)){
                                        this.gravity = Gravity.CENTER
                                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                                        textSize = 14f

                                    }.lparams(width = matchParent, height = wrapContent)

                                    textView{
                                        id = tv_player_weight
                                        this.gravity = Gravity.CENTER
                                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                                        textColor = ContextCompat.getColor(context, R.color.colorAccent)
                                    }.lparams(width = matchParent, height = wrapContent){
                                        margin = dip(5)
                                    }

                                }.lparams(width = dip(0), height = wrapContent) {
                                    weight = 1F
                                }
                            }.lparams(width = matchParent, height = wrapContent){
                                below(tv_player_position)
                                bottomMargin = dip(10)
                            }


                            linearLayout{
                                padding = dip(15)
                                orientation = LinearLayout.VERTICAL
                                gravity = Gravity.CENTER_HORIZONTAL

                                textView{
                                    id = tv_player_description
                                }.lparams(width = matchParent, height = wrapContent)

                            }.lparams(width = matchParent, height = wrapContent){
                                below(id_player_attributes)
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

    object PlayerDetailsIds {
        const val id_swipe_refresh = 1
        const val iv_player_thumbnail = 2
        const val tv_player_position = 3
        const val tv_player_height = 4
        const val tv_player_weight = 5
        const val tv_player_description = 6
        const val id_progress_bar = 7
        const val id_player_attributes = 8
    }
}