package appoutlet.gameoutlet.feature.latestdeals

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class LatestDealsViewTest : UiTest() {
    private val mockOnInputEvent = mockk<(LatestDealsInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should load`() {
        composeTestRule.setContent {
            LatestDealsView().ViewContent(LatestDealsUiState.Idle, mockOnInputEvent)
        }

        verify { mockOnInputEvent.invoke(LatestDealsInputEvent.Load) }
    }

    @Test
    fun `should show loading state`() {
        composeTestRule.setContent {
            LatestDealsView().ViewContent(LatestDealsUiState.Loading, mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("loadingIndicator")
            .assertIsDisplayed()
    }

    @Test
    fun `should show error state`() {
        composeTestRule.setContent {
            LatestDealsView().ViewContent(LatestDealsUiState.Error, mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("errorIndicator")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(i18n.tr("Try again"))
            .performClick()

        composeTestRule.waitForIdle()

        verify { mockOnInputEvent.invoke(LatestDealsInputEvent.Load) }
    }

    @Test
    fun `should show success state`() {
        val dealsFixture = fixture<LatestDealsUiState.Loaded>()
        val firstDeal = dealsFixture.uiModels.first()

        composeTestRule.setContent {
            LatestDealsView().ViewContent(dealsFixture, mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("screenTitle")
            .assert(hasText(i18n.tr("Latest deals")))

        composeTestRule.onNodeWithText(firstDeal.gameTitle)
            .performClick()

        composeTestRule.waitForIdle()

        verify { mockOnInputEvent.invoke(LatestDealsInputEvent.DealClicked(firstDeal)) }
    }

    @Test
    fun `should navigate to search`() {
        val dealsFixture = fixture<LatestDealsUiState.Loaded>()

        composeTestRule.setContent {
            LatestDealsView().ViewContent(dealsFixture, mockOnInputEvent)
        }

        composeTestRule.print()

        composeTestRule.onNodeWithText(i18n.tr("Take me to search screen"))
            .performScrollTo()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(i18n.tr("Take me to search screen"))
            .assertIsDisplayed()
            .performClick()

        composeTestRule.waitForIdle()

        verify { mockOnInputEvent.invoke(LatestDealsInputEvent.ToSearch) }
    }
}
