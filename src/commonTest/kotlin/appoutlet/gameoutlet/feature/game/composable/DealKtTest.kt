package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.feature.game.GameDealUiModel
import appoutlet.gameoutlet.feature.game.GameInputEvent
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

internal class DealKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(GameInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should show deal`() {
        val fixtureGameDealUiModel = fixture<GameDealUiModel>().copy(showNormalPrice = true)

        composeTestRule.setContent {
            Deal(item = fixtureGameDealUiModel, onInputEvent = mockOnInputEvent)
        }

        // Show store name
        composeTestRule.onNodeWithText(fixtureGameDealUiModel.store.name)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(fixtureGameDealUiModel.salePrice)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(fixtureGameDealUiModel.normalPrice)
            .assertIsDisplayed()
    }

    @Test
    fun `should show deal - no normal price`() {
        val fixtureGameDealUiModel = fixture<GameDealUiModel>().copy(showNormalPrice = false)

        composeTestRule.setContent {
            Deal(item = fixtureGameDealUiModel, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithText(fixtureGameDealUiModel.normalPrice)
            .assertDoesNotExist()
    }

    @Test
    fun `should emit event when click`() {
        val fixtureGameDealUiModel = fixture<GameDealUiModel>()

        composeTestRule.setContent {
            Deal(item = fixtureGameDealUiModel, onInputEvent = mockOnInputEvent)
        }

        // Show store name
        composeTestRule.onNodeWithTag(fixtureGameDealUiModel.id)
            .performClick()

        verify { mockOnInputEvent.invoke(GameInputEvent.DealClicked(fixtureGameDealUiModel)) }
    }
}