package com.example.ahmadqosam.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ahmadqosam.footballmatchschedule.fragment.FavoriteMatchFragment
import com.example.ahmadqosam.footballmatchschedule.fragment.FavoriteTeamFragment

class FavoritesPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm){

    // list containing fragments
    private  val pages = listOf(
        FavoriteMatchFragment(),
        FavoriteTeamFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Matches"
            1 -> "Teams"
            else -> "Matches"
        }
    }
}