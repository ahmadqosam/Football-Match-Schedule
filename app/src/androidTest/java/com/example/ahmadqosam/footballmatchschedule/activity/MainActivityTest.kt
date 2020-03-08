package com.example.ahmadqosam.footballmatchschedule.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.ahmadqosam.footballmatchschedule.R
import com.example.ahmadqosam.footballmatchschedule.R.id.*
import com.example.ahmadqosam.footballmatchschedule.fragment.FavoriteTeamFragment
import com.example.ahmadqosam.footballmatchschedule.fragment.TeamPlayersFragment
import com.example.ahmadqosam.footballmatchschedule.fragment.TeamsFragment
import com.example.ahmadqosam.footballmatchschedule.view.FavoriteMatchListUI
import com.example.ahmadqosam.footballmatchschedule.view.MatchListUI
import com.example.ahmadqosam.footballmatchschedule.view.MatchListUI.MatchIds.list_match
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLastMatchRecyclerViewBehaviour() {
        onView(allOf(withId(list_match), isDisplayed()))
        Thread.sleep(1500)
        onView(allOf(withId(list_match), isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(allOf(withId(list_match), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testNextMatchRecyclerViewBehaviour() {
        onView(allOf(withId(list_match), isDisplayed()))
        Thread.sleep(1500)
        onView(allOf(withId(list_match), isDisplayed())).perform(swipeLeft())
        onView(allOf(withId(list_match), isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(allOf(withId(list_match), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testChangeLeagueLastMatchList() {
        onView(withId(viewpager_matches))
            .check(matches(isDisplayed()))
        Thread.sleep(1500)
        val spinner = onView(allOf(withId(MatchListUI.MatchIds.id_spinner), isDisplayed()))
        Thread.sleep(1500)
        spinner.perform(click())
        onView(withText("Spanish La Liga")).perform(click())
        onView(withText("Barcelona"))
            .check(matches(isDisplayed()))
        onView(withText("Barcelona")).perform(click())
    }

    @Test
    fun testChangeLeagueNextMatchList() {
        val viewPager = onView(withId(viewpager_matches))
            .check(matches(isDisplayed()))
        viewPager.perform(swipeLeft())
        Thread.sleep(1500)
        val spinner = onView(allOf(withId(MatchListUI.MatchIds.id_spinner), isDisplayed()))
        Thread.sleep(1500)
        spinner.perform(click())
        onView(withText("Spanish La Liga")).perform(click())
        onView(withText("Barcelona"))
            .check(matches(isDisplayed()))
        onView(withText("Barcelona")).perform(click())
    }

    @Test
    fun testTeamsRecyclerViewBehaviour() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(teams)).perform(click())
        val listTeam = onView(allOf(withId(TeamsFragment.TeamsUiIds.rv_list_team), isDisplayed()))
        Thread.sleep(1500)
        listTeam.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        listTeam.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testPlayerDetailsBehaviour() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(teams)).perform(click())
        val listTeam = onView(allOf(withId(TeamsFragment.TeamsUiIds.rv_list_team), isDisplayed()))
        Thread.sleep(1500)
        listTeam.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        val viewPager = onView(withId(viewpager_team_detail))
            .check(matches(isDisplayed()))
        viewPager.perform(swipeLeft())
        onView(allOf(withId(TeamPlayersFragment.PlayersUiIds.rv_list_team), isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun testChangeLeagueTeamsTabs() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(teams)).perform(click())
        val spinner = onView(allOf(withId(TeamsFragment.TeamsUiIds.id_spinner), isDisplayed()))
        Thread.sleep(1500)
        spinner.perform(click())
        onView(withText("Spanish La Liga")).perform(click())
        onView(withText("Barcelona"))
            .check(matches(isDisplayed()))
        onView(withText("Barcelona")).perform(click())
    }

    @Test
    fun testFavoriteUnFavoriteTeam() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(teams)).perform(click())
        val listTeam = onView(allOf(withId(TeamsFragment.TeamsUiIds.rv_list_team), isDisplayed()))
        Thread.sleep(1500)
        listTeam.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        listTeam.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        Thread.sleep(1500)
        val favoriteButton = onView(
            allOf(withId(R.id.add_to_favorite), isDisplayed())
        )
        Thread.sleep(1000)
        favoriteButton.perform(click())
        onView(withText(R.string.favorite_success))
            .check(matches(isDisplayed()))
        pressBack()
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        Thread.sleep(1500)
        val viewPager = onView(withId(viewpager_favorites))
            .check(matches(isDisplayed()))
        viewPager.perform(swipeLeft())
        onView(allOf(withId(FavoriteTeamFragment.Ids.favorite_team_list), isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(allOf(withId(FavoriteTeamFragment.Ids.favorite_team_list), isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
        favoriteButton.perform(click())
        onView(withText(R.string.unfavorite_success))
            .check(matches(isDisplayed()))
    }


    @Test
    fun testFavoriteUnFavoriteMatch() {
        onView(allOf(withId(list_match), isDisplayed()))
        Thread.sleep(1500)
        onView(allOf(withId(list_match), isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(allOf(withId(list_match), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        Thread.sleep(1500)
        val favoriteButton = onView(
            allOf(withId(R.id.add_to_favorite), isDisplayed())
        )
        Thread.sleep(2000)
        favoriteButton.perform(click())
        onView(withText(R.string.favorite_success))
            .check(matches(isDisplayed()))
        pressBack()
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        Thread.sleep(1500)
        onView(allOf(withId(FavoriteMatchListUI.Ids.favorite_match_list), isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(allOf(withId(FavoriteMatchListUI.Ids.favorite_match_list), isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
        favoriteButton.perform(click())
        onView(withText(R.string.unfavorite_success))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSearchMatchBehaviour() {
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.action_search), withContentDescription("search"),
                childAtPosition(childAtPosition(withId(R.id.action_bar), 1), 0),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(withId(R.id.search_plate),
                        childAtPosition(withId(R.id.search_edit_frame), 1)),
                    0),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(click())

        val searchAutoComplete2 = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(replaceText("arsenal"), closeSoftKeyboard())

        val searchAutoComplete3 = onView(
            allOf(
                withId(R.id.search_src_text), withText("arsenal"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete3.perform(pressImeActionButton())

        Thread.sleep(1000)

        val recyclerView = onView(
            allOf(
                withId(R.id.list_match),
                childAtPosition(
                    withClassName(Matchers.`is`("android.widget.RelativeLayout")),
                    0
                )
            )
        )
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun testSearchTeamBehaviour() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(teams)).perform(click())
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.action_search), withContentDescription("search"),
                childAtPosition(childAtPosition(withId(R.id.action_bar), 1), 0),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(withId(R.id.search_plate),
                        childAtPosition(withId(R.id.search_edit_frame), 1)),
                    0),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(click())

        val searchAutoComplete2 = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(replaceText("arsenal"), closeSoftKeyboard())

        val searchAutoComplete3 = onView(
            allOf(
                withId(R.id.search_src_text), withText("arsenal"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete3.perform(pressImeActionButton())

        Thread.sleep(1000)

        val recyclerView = onView(
            allOf(
                withId(R.id.list_team)
            )
        )
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}