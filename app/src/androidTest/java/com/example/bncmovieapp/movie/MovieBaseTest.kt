package com.example.bncmovieapp.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.bncmovieapp.DrawableMatcher.withDrawable
import com.example.bncmovieapp.R

open class MovieBaseTest {
    fun testState(movieTitle: Int, actorTitle: Int, heart: Int, movie: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(movieTitle)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_actor))
            .check(ViewAssertions.matches(ViewMatchers.withText(actorTitle)))

        Espresso.onView(ViewMatchers.withId(R.id.iv_movie)).check(
                ViewAssertions.matches(withDrawable(movie))
        )
        Espresso.onView(ViewMatchers.withId(R.id.iv_favorite_image)).check(
            ViewAssertions.matches(withDrawable(heart))
        )
    }

    fun testHeartClick(heart: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.iv_favorite_image)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.iv_favorite_image)).check(
            ViewAssertions.matches(withDrawable(heart))
        )
    }
}