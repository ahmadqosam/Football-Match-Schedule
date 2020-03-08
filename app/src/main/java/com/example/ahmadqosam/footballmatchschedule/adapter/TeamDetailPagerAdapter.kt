package com.example.ahmadqosam.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ahmadqosam.footballmatchschedule.fragment.TeamOverviewFragment
import com.example.ahmadqosam.footballmatchschedule.fragment.TeamPlayersFragment

class TeamDetailPagerAdapter(fm: FragmentManager?, teamId: String): FragmentPagerAdapter(fm){

    // list containing fragments
    private  val pages = listOf(
        TeamOverviewFragment.newInstance(teamId),
        TeamPlayersFragment.newInstance(teamId)
        )

    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Overview"
            1 -> "Players"
            else -> "Overview"
        }
    }
}