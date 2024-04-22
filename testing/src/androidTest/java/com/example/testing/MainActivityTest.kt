package com.example.testing


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counterScreen_isDisplayed() {
        // Set content to CounterScreen
        composeTestRule.setContent {
            CounterScreen()
        }

        // Verify "Counter: 0" text is displayed
        composeTestRule
            .onNodeWithText("Counter: 0")
            .assertExists()
            .assertIsDisplayed()
        Thread.sleep(10000)

        // Click on "Increase Counter" button
        composeTestRule
            .onNodeWithText("Increase Counter")
            .assertExists()
            .performClick()
        Thread.sleep(10000)

        // Verify "Counter: 1" text is displayed
        composeTestRule
            .onNodeWithText("Counter: 1")
            .assertExists()
            .assertIsDisplayed()
        // keep the screen
        Thread.sleep(10000)
    }
}
