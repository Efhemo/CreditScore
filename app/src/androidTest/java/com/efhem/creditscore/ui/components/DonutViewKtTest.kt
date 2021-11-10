package com.efhem.creditscore.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.efhem.creditscore.ui.theme.CreditScoreTheme
import org.junit.Rule
import org.junit.Test


class DonutViewKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun donutViewDisplayAndClickable() {

        composeTestRule.setContent {
            CreditScoreTheme {
                DonutView()
            }
        }

        val button = composeTestRule.onNode(hasTestTag("DonutViewTag"), useUnmergedTree = true)
        button.assertIsDisplayed()
        button.performClick()
    }
}