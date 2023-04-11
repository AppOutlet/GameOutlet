package appoutlet.gameoutlet.feature.game

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class GameViewTest : UiTest() {
    private val mockOnInputEvent = mockk<(GameInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should load on idle state`() {
        val fixtureGameNavArgs = fixture<GameNavArgs>()

        composeTestRule.setContent {
            GameView(fixtureGameNavArgs).ViewContent(GameUiState.Idle, mockOnInputEvent)
        }

        verify { mockOnInputEvent.invoke(GameInputEvent.Load(fixtureGameNavArgs)) }
    }

    @Test
    fun `should show error state`() {
        val fixtureGameNavArgs = fixture<GameNavArgs>()

        composeTestRule.setContent {
            GameView(fixtureGameNavArgs).ViewContent(GameUiState.Error, mockOnInputEvent)
        }

        composeTestRule.onNodeWithText(i18n.tr("Try again"))
            .assertIsDisplayed()
            .performClick()

        verify { mockOnInputEvent.invoke(GameInputEvent.Load(fixtureGameNavArgs)) }
    }

    @Test
    fun `should show loading state`() {
        val fixtureGameNavArgs = fixture<GameNavArgs>()

        composeTestRule.setContent {
            GameView(fixtureGameNavArgs).ViewContent(GameUiState.Loading, mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("loadingIndicator")
            .assertIsDisplayed()
    }

    @Test
    fun `should show loaded state`() {
        val fixtureGameNavArgs = fixture<GameNavArgs>()
        val fixtureGameUiModel= fixture<GameUiModel>()

        composeTestRule.setContent {
            GameView(fixtureGameNavArgs)
                .ViewContent(GameUiState.Loaded(fixtureGameUiModel), mockOnInputEvent)
        }

        composeTestRule.onNodeWithText(fixtureGameUiModel.title)
            .assertIsDisplayed()
    }
}
