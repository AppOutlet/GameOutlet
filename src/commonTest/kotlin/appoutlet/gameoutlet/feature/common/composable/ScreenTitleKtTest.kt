package appoutlet.gameoutlet.feature.common.composable

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import appoutlet.gameoutlet.core.testing.UiTest
import kotlin.test.Test

class ScreenTitleKtTest : UiTest() {
    @Test
    fun `should show page title`() {
        val screenTitleFixture = fixture<String>()

        composeTestRule.setContent { ScreenTitle(text = screenTitleFixture) }

        composeTestRule.onNodeWithTag("screenTitle")
            .assert(hasText(screenTitleFixture))
    }
}