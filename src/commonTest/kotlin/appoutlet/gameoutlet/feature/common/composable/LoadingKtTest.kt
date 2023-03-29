package appoutlet.gameoutlet.feature.common.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import appoutlet.gameoutlet.core.testing.UiTest
import kotlin.test.Test

class LoadingKtTest : UiTest() {
    @Test
    fun `should show loading view`() {
        val textFixture = fixture<String>()

        composeTestRule.setContent {
            Loading(text = textFixture)
        }

        composeTestRule.onNodeWithText(textFixture)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("loadingIndicator")
            .assertIsDisplayed()
    }
}
