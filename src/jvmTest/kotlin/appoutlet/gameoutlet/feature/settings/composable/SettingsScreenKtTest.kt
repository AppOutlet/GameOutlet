package appoutlet.gameoutlet.feature.settings.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.feature.settings.SettingsInputEvent
import appoutlet.gameoutlet.feature.settings.SettingsUiState
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class SettingsScreenKtTest : UiTest() {
    private val mockOnInputEvent = mockk<(SettingsInputEvent) -> Unit>(relaxed = true)

    @Test
    fun `should show screen title`() {
        val fixtureUiState = fixture<SettingsUiState>()

        composeTestRule.setContent {
            SettingsScreen(uiState = fixtureUiState, onInputEvent = mockOnInputEvent)
        }

        composeTestRule.onNodeWithTag("screenTitle")
            .assertIsDisplayed()
    }

    @Test
    fun `should show theme button`() {
        val fixtureUiState = fixture<SettingsUiState.Loaded>()

        composeTestRule.setContent {
            SettingsScreen(uiState = fixtureUiState, onInputEvent = mockOnInputEvent)
        }

        with(fixtureUiState.settingsViewData.themeViewData.lightButton) {
            composeTestRule.onNodeWithText(name)
                .assertIsDisplayed()
                .performClick()

            verify { mockOnInputEvent.invoke(inputEvent) }
        }

        with(fixtureUiState.settingsViewData.themeViewData.darkButton) {
            composeTestRule.onNodeWithText(name)
                .assertIsDisplayed()
                .performClick()

            verify { mockOnInputEvent.invoke(inputEvent) }
        }

        with(fixtureUiState.settingsViewData.themeViewData.systemThemeButton) {
            composeTestRule.onNodeWithText(name)
                .assertIsDisplayed()
                .performClick()

            verify { mockOnInputEvent.invoke(inputEvent) }
        }
    }
}
