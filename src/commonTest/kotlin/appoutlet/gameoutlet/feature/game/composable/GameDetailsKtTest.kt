package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.game.GameInputEvent
import appoutlet.gameoutlet.feature.game.GameUiModel
import io.mockk.mockk
import kotlin.test.Test

internal class GameDetailsKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(GameInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should show game details`() {
        val fixtureUiModel = fixture<GameUiModel>().copy(deals = listOf(fixture()))

        composeTestRule.setContent {
            GameDetails(uiState = fixtureUiModel, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithText(fixtureUiModel.title)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(i18n.tr("Deals"))
            .assertIsDisplayed()

        fixtureUiModel.deals.forEach { deal ->
            val node = composeTestRule.onNodeWithText(deal.store.name)
            node.performScrollTo()
            composeTestRule.waitForIdle()
            node.assertIsDisplayed()
        }
    }
}
