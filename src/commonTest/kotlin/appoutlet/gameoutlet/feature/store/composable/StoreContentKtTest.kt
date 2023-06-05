package appoutlet.gameoutlet.feature.store.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.store.StoreInputEvent
import appoutlet.gameoutlet.feature.store.StoreUiState
import appoutlet.gameoutlet.feature.store.StoreViewData
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class StoreContentKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(StoreInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should navigate back`() {
        val fixtureStore = fixture<Store>()

        composeTestRule.setContent {
            StoreContent(
                store = fixtureStore,
                uiState = StoreUiState.Idle,
                onInputEvent = mockOnInputEvent,
            )
        }

        composeTestRule.onNodeWithTag("storeTopBar")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("navigateBack")
            .performClick()

        verify { mockOnInputEvent.invoke(StoreInputEvent.NavigateBack) }
    }

    @Test
    fun `should load when its idle`() {
        val fixtureStore = fixture<Store>()

        composeTestRule.setContent {
            StoreContent(
                store = fixtureStore,
                uiState = StoreUiState.Idle,
                onInputEvent = mockOnInputEvent,
            )
        }

        verify { mockOnInputEvent.invoke(StoreInputEvent.Load(fixtureStore)) }
    }

    @Test
    fun `Should show error state`() {
        val fixtureStore = fixture<Store>()

        composeTestRule.setContent {
            StoreContent(
                store = fixtureStore,
                uiState = StoreUiState.Error,
                onInputEvent = mockOnInputEvent,
            )
        }

        composeTestRule.onNodeWithText("Try again")
            .performClick()

        verify { mockOnInputEvent.invoke(StoreInputEvent.Load(fixtureStore)) }
    }

    @Test
    fun `Should show loading state`() {
        val fixtureStore = fixture<Store>()

        composeTestRule.setContent {
            StoreContent(
                store = fixtureStore,
                uiState = StoreUiState.Loading,
                onInputEvent = mockOnInputEvent,
            )
        }

        composeTestRule.onNodeWithTag("loadingIndicator")
            .assertIsDisplayed()
    }


    @Test
    fun `Should show loaded deals`() {
        val fixtureStore = fixture<Store>()
        val fixtureLoadedState = StoreUiState.Loaded(StoreViewData(listOf(fixture(), fixture())))

        composeTestRule.setContent {
            StoreContent(
                store = fixtureStore,
                uiState = fixtureLoadedState,
                onInputEvent = mockOnInputEvent,
            )
        }

        fixtureLoadedState.viewData.deals.forEach { deal ->
            composeTestRule.onNodeWithText(deal.title)
                .performScrollTo()
                .assertIsDisplayed()
                .performClick()

            composeTestRule.onNodeWithText(deal.currentPrice)
                .assertIsDisplayed()

            composeTestRule.onNodeWithText(deal.normalPrice!!)
                .assertIsDisplayed()

            verify { mockOnInputEvent.invoke(deal.inputEvent) }
        }
    }
}