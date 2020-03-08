package com.example.ahmadqosam.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.model.Player
import com.example.ahmadqosam.footballmatchschedule.view.PlayerUI
import com.example.ahmadqosam.footballmatchschedule.view.PlayerUI.PlayerIds.iv_player_avatar
import com.example.ahmadqosam.footballmatchschedule.view.PlayerUI.PlayerIds.tv_player_name
import com.example.ahmadqosam.footballmatchschedule.view.PlayerUI.PlayerIds.tv_player_position
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find


class PlayerAdapter(private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun getItemCount(): Int = players.size

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val playerAvatar: ImageView = view.find(iv_player_avatar)
    private val playerName: TextView = view.find(tv_player_name)
    private val playerPosition: TextView = view.find(tv_player_position)

    fun bindItem(player: Player, listener: (Player) -> Unit) {
        Picasso.get()
            .load(player.playerAvatar)
            .placeholder(R.drawable.ic_default_avatar)
            .error(R.drawable.ic_default_avatar)
            .into(playerAvatar)
        playerName.text = player.playerName
        playerPosition.text = player.playerPosition
        itemView.setOnClickListener { listener(player) }
    }
}