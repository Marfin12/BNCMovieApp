package com.example.bncmovieapp.home

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.bncmovieapp.R

open class HomeBaseTest {
    fun testState() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
    }
}