package com.example.bncmovieapp.movie

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.bncmovieapp.R
import com.example.bncmovieapp.core.domain.model.Movie
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumentation tests to be run on a physical device or emulator.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieTests : MovieBaseTest() {

    val mockedMovie = Movie(
        "1",
        "movie_title",
        "2021",
        "4",
        "",
        false,
        "this is movie",
        arrayListOf(),
        "horror",
        "60 minute"
    )

    @get:Rule
    val mActivityTestRule: ActivityTestRule<MovieActivity> =
        object : ActivityTestRule<MovieActivity>(MovieActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, MovieActivity::class.java).apply {
                    putExtra(MovieActivity.MOVIE_DATA, mockedMovie)
                }
            }
        }

    /**
     * Test the view components of the pick lemon state
     */
    @Test
    fun `test_movie_UI`() {
        testState(
            R.string.movie_title,
            R.string.detail_actor,
            R.drawable.ic_unfavorite_foreground,
            R.drawable.di
        )
    }

    /**
     * Test the view components of the pick lemon state
     */
    @Test
    fun `test_click_heart_to_note_favorite_movie`() {
        testHeartClick(R.drawable.ic_favorite_foreground)
    }
}