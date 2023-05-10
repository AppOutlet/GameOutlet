package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.feature.game.GameInputEvent
import appoutlet.gameoutlet.feature.game.GameUiModel
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class GameDetailsDealsListKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(GameInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should emit event when deal is clicked`() {
        val fixtureGameUiModel = fixture<GameUiModel>()

        composeTestRule.setContent {
            GameDetails(uiState = fixtureGameUiModel, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.print()

        fixtureGameUiModel.deals.forEach { deal ->
            composeTestRule.onNodeWithText(deal.store.name)
                .assertExists()
                .performScrollTo()

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithText(deal.store.name)
                .performClick()

            verify { mockOnInputEvent.invoke(GameInputEvent.DealClicked(deal)) }
        }
    }
}
