package com.example.ahmadqosam.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.adapter.MatchesPagerAdapter
import kotlinx.android.synthetic.main.fragment_matches.*

class MatchesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager_matches.adapter =
                MatchesPagerAdapter(childFragmentManager)
        tabs_matches.setupWithViewPager(viewpager_matches)
    }
}
