package appoutlet.gameoutlet.feature.gamesearch.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.gamesearch.GameSearchInputEvent
import appoutlet.gameoutlet.feature.gamesearch.GameSearchUiModel
import appoutlet.gameoutlet.feature.gamesearch.GameSearchUiState
import io.mockk.mockk
import io.mockk.verify
import name.kropp.kotlinx.gettext.tr
import kotlin.test.Test

class GameSearchContentKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(GameSearchInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should search`() {
        val fixtureSearchTermInitial = fixture<String>()
        val fixtureSearchTermFinal = fixture<String>()

        composeTestRule.setContent {
            GameSearchContent(GameSearchUiState.Idle(fixtureSearchTermInitial), mockOnInputEvent)
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("idleState")

        composeTestRule.onNodeWithTag("screenTitle")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("searchView").run {
            assertIsDisplayed()
            performTextClearance()
            performTextInput(fixtureSearchTermFinal)
        }

        composeTestRule.waitForIdle()

        verify { mockOnInputEvent.invoke(GameSearchInputEvent.Search(fixtureSearchTermFinal)) }
    }

    @Test
    fun `should show search results`() {
        val fixtureSearchTerm = fixture<String>()
        val fixtureGames = listOf(
            fixture<GameSearchUiModel>(),
            fixture<GameSearchUiModel>(),
            fixture<GameSearchUiModel>(),
        )
        val fixtureUiState = GameSearchUiState.Loaded(fixtureSearchTerm, fixtureGames)

        composeTestRule.setContent {
            GameSearchContent(fixtureUiState, mockOnInputEvent)
        }

        composeTestRule.waitForIdle()

        fixtureGames.forEach { uiModel ->
            composeTestRule.onNodeWithText(uiModel.title)
                .assertIsDisplayed()
                .performClick()

            verify { mockOnInputEvent.invoke(GameSearchInputEvent.GameClicked(uiModel)) }
        }
    }

    @Test
    fun `should show error state`() {
        val fixtureUiState = GameSearchUiState.Error("", emptyList())

        composeTestRule.setContent {
            GameSearchContent(fixtureUiState, mockOnInputEvent)
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(i18n.tr("Try again"))
            .assertIsDisplayed()
            .performClick()

        verify { mockOnInputEvent.invoke(GameSearchInputEvent.Search("")) }
    }

    @Test
    fun `should show empty state`() {
        val fixtureUiState = GameSearchUiState.Loaded("", emptyList())

        composeTestRule.setContent {
            GameSearchContent(fixtureUiState, mockOnInputEvent)
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("emptyState")
            .assertIsDisplayed()
    }
}
