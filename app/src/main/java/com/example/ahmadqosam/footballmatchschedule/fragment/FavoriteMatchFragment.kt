package com.example.ahmadqosam.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmadqosam.footballmatchschedule.activity.MatchDetailActivity
import com.example.ahmadqosam.footballmatchschedule.adapter.FavoriteMatchAdapter
import com.example.ahmadqosam.footballmatchschedule.db.FavoriteMatch
import com.example.ahmadqosam.footballmatchschedule.db.database
import com.example.ahmadqosam.footballmatchschedule.view.FavoriteMatchListUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteMatchFragment : Fragment() {

    private var favorites: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: FavoriteMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = FavoriteMatchAdapter(favorites) {
            startActivity<MatchDetailActivity>(
                "matchId" to "${it.matchId}",
                "homeTeamId" to "${it.homeTeamId}",
                "awayTeamId" to "${it.awayTeamId}"
            )
        }

        val view =  FavoriteMatchListUI<Fragment>(adapter)
            .createView(AnkoContext.create(requireContext(), this))

        swipeRefresh = view.findViewById(FavoriteMatchListUI.Ids.swipe_refresh)
        swipeRefresh.onRefresh {
            showFavorite()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}
