package com.example.ahmadqosam.footballmatchschedule.view

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.activity.MatchDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchDetailsUI(private val mContext: Context) : AnkoComponent<MatchDetailActivity>{

    override fun createView(ui: AnkoContext<MatchDetailActivity>): View = with(ui){

        swipeRefreshLayout {

            id = Ids.swipe_refresh_layout

            setColorSchemeResources(
                R.color.colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

            relativeLayout{
                lparams (width = matchParent, height = wrapContent)

                linearLayout {
                    lparams (width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL
                    padding = dip(16)

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        textView {
                            id = Ids.tv_date
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = ContextCompat.getColor(context,R.color.colorPrimary)
                        }.lparams(width = matchParent, height = wrapContent){
                            bottomMargin = dip(16)
                        }

                        linearLayout {
                            isBaselineAligned = false
                            orientation = LinearLayout.HORIZONTAL

                            linearLayout {
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.VERTICAL

                                imageView {
                                    contentDescription = mContext.getString(R.string.home_team_badge)
                                    id = Ids.home_team_badge
                                }.lparams(width = dip(50), height = dip(50)){
                                    bottomMargin = dip(16)
                                }

                                textView(R.string.home_team) {
                                    id = Ids.tv_home_team
                                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                                }.lparams(width = matchParent, height = wrapContent)

                            }.lparams(width = wrapContent, height = wrapContent) {
                                weight = 1F
                            }

                            linearLayout {
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL

                                textView(R.string.default_score) {
                                    id = Ids.tv_home_score
                                    typeface = Typeface.DEFAULT_BOLD
                                }.lparams(width = wrapContent, height = wrapContent)

                                textView(R.string.versus).lparams(width = wrapContent, height = wrapContent){
                                    leftMargin = dip(8)
                                    rightMargin = dip(8)
                                }

                                textView(R.string.default_score) {
                                    id = Ids.tv_away_score
                                    typeface = Typeface.DEFAULT_BOLD
                                }.lparams(width = wrapContent, height = wrapContent)

                            }.lparams(width = dip(0), height = matchParent) {
                                weight = 1F
                            }

                            linearLayout {
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.VERTICAL

                                imageView {
                                    contentDescription = mContext.getString(R.string.away_team_badge)
                                    id = Ids.away_team_badge
                                }.lparams(width = dip(50), height = dip(50)){
                                    bottomMargin = dip(16)
                                }

                                textView(R.string.away_team) {
                                    id = Ids.tv_away_team
                                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                                }.lparams(width = matchParent, height = wrapContent)
                            }.lparams(width = wrapContent, height = wrapContent) {
                                weight = 1F
                            }
                        }.lparams(width = matchParent, height = wrapContent)

                    }.lparams(width = matchParent, height = wrapContent){
                        bottomMargin = dip(16)
                    }

                    view {
                        backgroundResource = R.color.light_gray
                    }.lparams(width = matchParent, height = dip(1)){
                        bottomMargin = dip(16)
                    }

                    linearLayout {
                        id = Ids.scorer_data
                        orientation = LinearLayout.HORIZONTAL

                        textView {
                            id = Ids.tv_home_scorer
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                        linearLayout().lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                        textView {
                            id = Ids.tv_away_scorer
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                    }.lparams(width = matchParent, height = wrapContent){
                        bottomMargin = dip(16)
                    }

                    view {
                        backgroundResource = R.color.light_gray
                    }.lparams(width = matchParent, height = dip(1)){
                        bottomMargin = dip(16)
                    }

                    linearLayout {
                        id = Ids.shots_data
                        orientation = LinearLayout.HORIZONTAL

                        textView(R.string.default_shots) {
                            id = Ids.tv_home_shots
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                        textView(R.string.shots) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                        textView(R.string.default_shots) {
                            id = Ids.tv_away_shots
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                    }.lparams(width = matchParent, height = wrapContent){
                        bottomMargin = dip(16)
                    }

                    linearLayout {
                        id = Ids.yellow_card_data
                        orientation = LinearLayout.HORIZONTAL

                        textView(R.string.default_yellow_cards) {
                            id = Ids.tv_home_yellow_cards
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                        textView(R.string.yellow_cards) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                        textView(R.string.default_yellow_cards) {
                            id = Ids.tv_away_yellow_cards
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                    }.lparams(width = matchParent, height = wrapContent){
                        bottomMargin = dip(16)
                    }

                    linearLayout {
                        id = Ids.red_card_data
                        orientation = LinearLayout.HORIZONTAL

                        textView(R.string.default_red_cards) {
                            id = Ids.tv_home_red_cards
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                        textView(R.string.red_cards) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                        textView(R.string.default_red_cards) {
                            id = Ids.tv_away_red_cards
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1F
                        }

                    }.lparams(width = matchParent, height = wrapContent){
                        bottomMargin = dip(16)
                    }
                }

                progressBar {
                    id = Ids.progress_bar
                }.lparams{
                    centerHorizontally()
                }
            }
        }


    }

    object Ids {
        const val away_team_badge = 1
        const val home_team_badge = 2
        const val red_card_data = 3
        const val scorer_data = 4
        const val shots_data = 5
        const val tv_away_red_cards = 6
        const val tv_away_score = 7
        const val tv_away_scorer = 8
        const val tv_away_shots = 9
        const val tv_away_team = 10
        const val tv_away_yellow_cards = 11
        const val tv_date = 12
        const val tv_home_red_cards = 13
        const val tv_home_score = 14
        const val tv_home_scorer = 15
        const val tv_home_shots = 16
        const val tv_home_team = 17
        const val tv_home_yellow_cards = 18
        const val yellow_card_data = 19
        const val progress_bar = 20
        const val swipe_refresh_layout = 21
    }
}