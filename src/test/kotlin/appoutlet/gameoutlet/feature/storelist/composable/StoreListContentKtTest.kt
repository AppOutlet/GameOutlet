package appoutlet.gameoutlet.feature.storelist.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.storelist.StoreListInputEvent
import appoutlet.gameoutlet.feature.storelist.StoreListUiState
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class StoreListContentKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(StoreListInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should load whe Idle`() {
        val fixtureState = StoreListUiState.Idle

        composeTestRule.setContent {
            StoreListContent(uiState = fixtureState, onInputEvent = mockOnInputEvent)
        }

        verify { mockOnInputEvent.invoke(StoreListInputEvent.Load) }
    }

    @Test
    fun `should show error state`() {
        val fixtureState = StoreListUiState.Error

        composeTestRule.setContent {
            StoreListContent(uiState = fixtureState, onInputEvent = mockOnInputEvent)
        }

        // Should show error state
        composeTestRule.onNodeWithTag("errorLayout")
            .assertIsDisplayed()

        // Should load again when press "try again"
        composeTestRule.onNodeWithText(i18n.tr("Try again"))
            .performClick()

        verify { mockOnInputEvent.invoke(StoreListInputEvent.Load) }
    }

    @Test
    fun `Should show loading state`() {
        val fixtureState = StoreListUiState.Loading

        composeTestRule.setContent {
            StoreListContent(uiState = fixtureState, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("loadingIndicator")
            .assertIsDisplayed()
    }

    @Test
    fun `should show store list`() {
        val fixtureState = fixture<StoreListUiState.Loaded>()

        composeTestRule.setContent {
            StoreListContent(uiState = fixtureState, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("screenTitle")
            .assertIsDisplayed()

        fixtureState.stores.forEach { uiModel ->
            composeTestRule.onNodeWithText(uiModel.name)
                .assertIsDisplayed()
                .performClick()

            verify { mockOnInputEvent.invoke(uiModel.inputEvent) }
        }
    }
}
