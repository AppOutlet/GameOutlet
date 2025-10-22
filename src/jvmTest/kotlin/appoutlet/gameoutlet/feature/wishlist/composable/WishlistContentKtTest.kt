package appoutlet.gameoutlet.feature.wishlist.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.wishlist.WishlistInputEvent
import appoutlet.gameoutlet.feature.wishlist.WishlistUiState
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

internal class WishlistContentKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(WishlistInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should load when screen in idle`() {
        composeTestRule.setContent {
            WishlistContent(uiState = WishlistUiState.Idle, onInputEvent = mockOnInputEvent)
        }

        verify { mockOnInputEvent.invoke(WishlistInputEvent.Load) }
    }

    @Test
    fun `should show error state`() {
        composeTestRule.setContent {
            WishlistContent(uiState = WishlistUiState.Error, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("errorIndicator")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("button")
            .performClick()

        verify { mockOnInputEvent.invoke(WishlistInputEvent.Load) }
    }

    @Test
    fun `should show loading state`() {
        composeTestRule.setContent {
            WishlistContent(uiState = WishlistUiState.Loading, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("loadingIndicator")
            .assertIsDisplayed()
    }

    @Test
    fun `should show saved games`() {
        val fixtureGames = fixture<WishlistUiState.Loaded>()

        composeTestRule.setContent {
            WishlistContent(uiState = fixtureGames, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("screenTitle")
            .assertIsDisplayed()

        fixtureGames.list.forEach { game ->
            val gameCard = composeTestRule.onNodeWithText(game.title)

            gameCard.performScrollTo()
            composeTestRule.waitForIdle()
            gameCard.performClick()

            verify { mockOnInputEvent.invoke(WishlistInputEvent.GameClicked(game)) }
        }
    }

    @Test
    fun `should show empty state`() {
        composeTestRule.setContent {
            WishlistContent(uiState = WishlistUiState.Loaded(emptyList()), onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithText(i18n.tr("You didn't save any game yet"))
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(i18n.tr("Take me to search screen"))
            .performClick()

        verify { mockOnInputEvent.invoke(WishlistInputEvent.GoToSearch) }
    }
}
