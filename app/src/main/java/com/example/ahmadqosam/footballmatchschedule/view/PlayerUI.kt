package com.example.ahmadqosam.footballmatchschedule.view

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.view.PlayerUI.PlayerIds.iv_player_avatar
import com.example.ahmadqosam.footballmatchschedule.view.PlayerUI.PlayerIds.tv_player_name
import com.example.ahmadqosam.footballmatchschedule.view.PlayerUI.PlayerIds.tv_player_position
import org.jetbrains.anko.*


class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL

                imageView {
                    id = iv_player_avatar
                }.lparams {
                    height = dip(70)
                    width = dip(70)
                }

                linearLayout {
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = tv_player_name
                        textSize = 18f
                        typeface = Typeface.DEFAULT_BOLD
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        setSingleLine(true)
                        ellipsize = TextUtils.TruncateAt.END
                    }.lparams {
                        bottomMargin = dip(5)
                    }

                    textView {
                        id = tv_player_position
                        textSize = 14f
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        setSingleLine(true)
                    }.lparams {
//                        margin = dip(15)
                    }

                }.lparams(width = wrapContent, height = wrapContent){
                    margin = dip(20)
                }

            }
        }
    }

    object PlayerIds {
        const val iv_player_avatar = 1
        const val tv_player_name = 2
        const val tv_player_position = 3
    }
}

