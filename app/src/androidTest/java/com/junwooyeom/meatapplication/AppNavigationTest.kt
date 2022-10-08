package com.junwooyeom.meatapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.accompanist.pager.ExperimentalPagerApi
import com.junwooyeom.meatapplication.presentation.view.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalPagerApi
@HiltAndroidTest
class AppNavigationTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun home_navigateWithHomeScreen() {
        composeTestRule.onNodeWithText("식품 리스트").assertIsDisplayed()
        composeTestRule.onNodeWithText("찜").performClick()
        composeTestRule.onNodeWithText("찜 목록").assertIsDisplayed()
        composeTestRule.onNodeWithText("식품 리스트").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("리스트").performClick()
        composeTestRule.onNodeWithText("식품 리스트").assertIsDisplayed()
        composeTestRule.onNodeWithText("찜 목록").assertIsNotDisplayed()
    }
}
