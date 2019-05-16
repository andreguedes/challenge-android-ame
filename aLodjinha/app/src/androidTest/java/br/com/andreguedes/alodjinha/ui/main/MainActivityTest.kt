package br.com.andreguedes.alodjinha.ui.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions.close
import android.support.test.espresso.contrib.DrawerActions.open
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.andreguedes.alodjinha.R
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    @Test
    fun verifyViews() {
        onView(withId(R.id.toolbar_main)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToHomeInDrawerLayout() {
        onView(withId(R.id.drawer_layout)).perform(open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_home))
        onView(withId(R.id.drawer_layout)).perform(close())
        onView(withText("Home")).inRoot(withDecorView(not(`is`(getActivity().window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToAboutInDrawerLayout() {
        onView(withId(R.id.drawer_layout)).perform(open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_about))
        onView(withId(R.id.drawer_layout)).perform(close())
        onView(withText("About")).inRoot(withDecorView(not(`is`(getActivity().window.decorView))))
            .check(matches(isDisplayed()))
    }

    private fun getActivity() = activityTestRule.activity

}