package com.example.ahmadqosam.footballmatchschedule.view

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = TeamIds.iv_team_badge
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = TeamIds.tv_team_name
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }

            }
        }
    }
}

object TeamIds {
    const val iv_team_badge = 1
    const val tv_team_name = 2
}