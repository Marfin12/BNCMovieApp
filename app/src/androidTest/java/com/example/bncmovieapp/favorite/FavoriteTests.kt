package com.example.bncmovieapp.favorite

import androidx.fragment.app.FragmentTransaction
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.bncmovieapp.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumentation tests to be run on a physical device or emulator.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class FavoriteTests: FavoriteBaseTest() {
    @get:Rule
    var activityRule: ActivityTestRule<*> = ActivityTestRule(
        MainActivity::class.java
    )

    @Test
    fun fragment_can_be_instantiated() {
        activityRule.activity.runOnUiThread {
            startHomeFragment()
        }
        testState()
    }

    private fun startHomeFragment(): FavoriteFragment {
        val activity: MainActivity = activityRule.activity as MainActivity
        val transaction: FragmentTransaction =
            activity.supportFragmentManager.beginTransaction()
        val favoriteFragment = FavoriteFragment()
        transaction.add(favoriteFragment, "favoriteFragment")
        transaction.commit()
        return favoriteFragment
    }
}