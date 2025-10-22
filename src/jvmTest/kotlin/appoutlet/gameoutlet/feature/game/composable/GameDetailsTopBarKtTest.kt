package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.feature.game.GameFavouriteButton
import appoutlet.gameoutlet.feature.game.GameInputEvent
import appoutlet.gameoutlet.feature.game.GameUiModel
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

internal class GameDetailsTopBarKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(GameInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should navigate back`() {
        val fixtureGameUiModel = fixture<GameUiModel>()

        composeTestRule.setContent {
            GameDetailsTopBar(uiState = fixtureGameUiModel, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithText(fixtureGameUiModel.title)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("navigation icon")
            .performClick()

        verify { mockOnInputEvent.invoke(GameInputEvent.NavigateBack) }
    }

    @Test
    fun `should save game`() {
        val fixtureFavButton = fixture<GameFavouriteButton>().copy(isSaved = false)
        val fixtureGameUiModel = fixture<GameUiModel>().copy(favouriteButton = fixtureFavButton)

        composeTestRule.setContent {
            GameDetailsTopBar(uiState = fixtureGameUiModel, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithContentDescription("save game icon")
            .performClick()

        verify { mockOnInputEvent.invoke(fixtureFavButton.inputEvent) }
    }

    @Test
    fun `should remove game`() {
        val fixtureFavButton = fixture<GameFavouriteButton>().copy(isSaved = true)
        val fixtureGameUiModel = fixture<GameUiModel>().copy(favouriteButton = fixtureFavButton)

        composeTestRule.setContent {
            GameDetailsTopBar(uiState = fixtureGameUiModel, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithContentDescription("remove game icon")
            .performClick()

        verify { mockOnInputEvent.invoke(fixtureFavButton.inputEvent) }
    }
}
