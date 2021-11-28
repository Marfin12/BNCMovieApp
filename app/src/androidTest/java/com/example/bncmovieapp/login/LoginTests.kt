package com.example.bncmovieapp

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.bncmovieapp.main.LoginActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation tests to be run on a physical device or emulator.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginTests : LoginBaseTest() {

    @Before
    fun setup() {
        launch(LoginActivity::class.java)
    }

    /**
     * Test the view components of the pick lemon state
     */
    @Test
    fun `test_initial_state`() {
        testState(R.string.login_text, R.drawable.ic_home_foreground)
    }

    /**
     * Test the view components of the pick lemon state
     */
    @Test
    fun `test_email_and_password_validation`() {
        testPressLoginButton()
    }

    /**
     * Test that the pick lemon functionality takes us to the "squeeze state"
     */
    @Test
    fun `test_typing_email_and_password_proceeds_to_get_validation`() {
        // Click image to progress state
        testTypingEmailAndPassword()
    }

    /**
     * Test the view components of the pick lemon state
     */
    @Test
    fun `test_login_button_to_navigate_home_screen`() {
        testTypingEmailAndPassword()
        testPressLoginButton()
    }
}