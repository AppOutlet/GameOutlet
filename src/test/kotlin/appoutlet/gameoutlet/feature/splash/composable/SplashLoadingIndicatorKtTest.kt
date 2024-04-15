package appoutlet.gameoutlet.feature.splash.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import kotlin.test.Test

class SplashLoadingIndicatorKtTest : UiTest() {
    @Test
    fun `should show loading indicator`() {
        composeTestRule.setContent {
            SplashLoadingIndicator()
        }

        composeTestRule.onNode(hasTestTag("splashLoadingIndicator"))
            .assertIsDisplayed()

        composeTestRule.onNode(hasText(i18n.tr("Loading")))
            .assertIsDisplayed()
    }
}
