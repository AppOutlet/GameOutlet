package appoutlet.gameoutlet.feature.about.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.feature.about.AboutInputEvent
import appoutlet.gameoutlet.feature.about.AboutUiState
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class AboutScreenKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(AboutInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should load if state is idle`() {
        composeTestRule.setContent {
            AboutScreen(AboutUiState.Idle, mockOnInputEvent)
        }

        verify { mockOnInputEvent.invoke(AboutInputEvent.Load) }
    }

    @Test
    fun `should load buttons`() {
        val fixtureLoadState = fixture<AboutUiState.Loaded>()

        composeTestRule.setContent {
            AboutScreen(fixtureLoadState, mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("appIcon")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("GameOutlet")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Version $VERSION")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Authors")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Messias Junior")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Contribute to project")
            .assertIsDisplayed()
            .performClick()

        verify { mockOnInputEvent.invoke(fixtureLoadState.contributeEvent!!) }

        composeTestRule.onNodeWithText("Buy me a coffee")
            .assertIsDisplayed()
            .performClick()

        verify { mockOnInputEvent.invoke(fixtureLoadState.donationEvent!!) }

        composeTestRule.onNodeWithTag("websiteIcon")
            .performClick()

        verify { mockOnInputEvent.invoke(fixtureLoadState.websiteEvent!!) }

        composeTestRule.onNodeWithTag("twitterIcon")
            .performClick()

        verify { mockOnInputEvent.invoke(fixtureLoadState.twitterEvent!!) }

        composeTestRule.onNodeWithTag("mastodonIcon")
            .performClick()

        verify { mockOnInputEvent.invoke(fixtureLoadState.mastodonEvent!!) }

        composeTestRule.onNodeWithTag("githubIcon")
            .performClick()

        verify { mockOnInputEvent.invoke(fixtureLoadState.githubEvent!!) }

        composeTestRule.onNodeWithText("Powered by AppOutlet")
            .assertIsDisplayed()
    }
}
