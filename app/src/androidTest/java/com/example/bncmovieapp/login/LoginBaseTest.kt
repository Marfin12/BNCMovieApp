package com.example.bncmovieapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.bncmovieapp.DrawableMatcher.withDrawable

open class LoginBaseTest {

    fun testState(textActionResource: Int, drawableResource: Int) {
        Espresso.onView(withId(R.id.tv_login))
            .check(ViewAssertions.matches(ViewMatchers.withText(textActionResource)))
        Espresso.onView(withId(R.id.iv_login)).check(
            ViewAssertions.matches(withDrawable(drawableResource))
        )
    }


    fun testTypingEmailAndPassword() {
        Espresso.onView(withId(R.id.et_email)).perform(click()).perform(
            typeText("anything@email.com"),
        )
        Espresso.onView(withId(R.id.et_email)).perform(closeSoftKeyboard())

        Espresso.onView(withId(R.id.et_password)).perform(click()).perform(
            typeText("password"),
        )
        Espresso.onView(withId(R.id.et_password)).perform(closeSoftKeyboard())
    }

    fun testPressLoginButton() {
        Espresso.onView(withId(R.id.btn_login)).perform(click())
    }
}
