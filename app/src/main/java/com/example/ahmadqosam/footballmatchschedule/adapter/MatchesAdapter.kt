package com.example.ahmadqosam.footballmatchschedule.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.adapter.MatchesAdapterIds.tv_away_score
import com.example.ahmadqosam.footballmatchschedule.adapter.MatchesAdapterIds.tv_away_team
import com.example.ahmadqosam.footballmatchschedule.adapter.MatchesAdapterIds.tv_date
import com.example.ahmadqosam.footballmatchschedule.adapter.MatchesAdapterIds.tv_home_score
import com.example.ahmadqosam.footballmatchschedule.adapter.MatchesAdapterIds.tv_home_team
import com.example.ahmadqosam.footballmatchschedule.model.Match
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*

class MatchesAdapter (private val matches: MutableList<Match>,
                      private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<MatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            MatchUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }
}

object MatchesAdapterIds {
    const val tv_away_score = 1
    const val tv_away_team = 2
    const val tv_date = 3
    const val tv_home_score = 4
    const val tv_home_team = 5
    const val match_container = 6
}

class MatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            linearLayout {
                orientation = LinearLayout.VERTICAL
                id = MatchesAdapterIds.match_container

                textView {
                    id = MatchesAdapterIds.tv_date
                    textAlignment =  View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(context,R.color.colorPrimary)
                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(8)
                    bottomMargin = dip(8)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    textView(R.string.home_team) {
                        id = MatchesAdapterIds.tv_home_team
                        textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.END
                        weight = 1F
                    }

                    linearLayout {
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.HORIZONTAL

                        textView(R.string.default_score) {
                            id = MatchesAdapterIds.tv_home_score
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent, height = wrapContent)

                        textView(R.string.versus).lparams(width = wrapContent, height = wrapContent){
                            leftMargin = dip(16)
                            rightMargin = dip(16)
                        }

                        textView(R.string.default_score) {
                            id = MatchesAdapterIds.tv_away_score
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent, height = wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        weight = 1F
                    }

                    textView(R.string.away_team) {
                        id = MatchesAdapterIds.tv_away_team
                    }.lparams(width = dip(0), height = wrapContent) {
                        weight = 1F
                    }

                }.lparams(width = matchParent, height = wrapContent){
                    bottomMargin = dip(16)
                }

                view {
                    backgroundColor = Color.LTGRAY
                }.lparams(width = matchParent, height = dip(1))
            }
        }
    }
}

class MatchViewHolder(override val containerView: View)
    : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val date: TextView = containerView.find(tv_date)
    private val homeTeam: TextView = containerView.find(tv_home_team)
    private val awayTeam: TextView = containerView.find(tv_away_team)
    private val homeScore: TextView = containerView.find(tv_home_score)
    private val awayScore: TextView = containerView.find(tv_away_score)

    fun bindItem(match: Match, listener: (Match) -> Unit) {
        date.text = match.date
        homeTeam.text = match.homeTeam
        awayTeam.text = match.awayTeam
        if(match.homeScore != null && match.awayScore != null){
            homeScore.text = match.homeScore
            awayScore.text = match.awayScore
        } else {
            homeScore.visibility = View.INVISIBLE
            awayScore.visibility = View.INVISIBLE
        }
        containerView.setOnClickListener {
            listener(match)
        }

    }
}