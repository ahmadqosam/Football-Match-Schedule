package com.example.ahmadqosam.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ahmadqosam.footballmatchschedule.fragment.LastMatchFragment
import com.example.ahmadqosam.footballmatchschedule.fragment.NextMatchFragment

class MatchesPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm){

    // list containing fragments
    private  val pages = listOf(
        LastMatchFragment(),
        NextMatchFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Last Match"
            1 -> "Next Match"
            else -> "Last Match"
        }
    }
}