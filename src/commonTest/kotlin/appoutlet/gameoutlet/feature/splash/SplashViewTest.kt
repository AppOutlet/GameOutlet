package appoutlet.gameoutlet.feature.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import cafe.adriel.voyager.navigator.Navigator
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewTest : UiTest() {
    private val mockOnInputEvent = mockk<(SplashInputEvent) -> Unit>()

    @Test
    fun `should load in idle state`() {
        every { mockOnInputEvent.invoke(any()) } returns Unit

        composeTestRule.setContent {
            SplashScreenContent(uiState = SplashUiState.Idle, onInputEvent = mockOnInputEvent)
        }

        verify { mockOnInputEvent.invoke(SplashInputEvent.Load) }

        // Application logo
        composeTestRule.onNode(hasContentDescription(i18n.tr("GameOutlet logo")))
            .assertIsDisplayed()

        // Application name
        composeTestRule.onNode(hasText("GameOutlet"))
            .assertIsDisplayed()

        // Application bottom text
        composeTestRule.onNode(hasText(i18n.tr("Powered by App Outlet")))
            .assertIsDisplayed()
    }

    @Test
    fun `should show loading state`() = runTest {
        composeTestRule.setContent {
            SplashScreenContent(uiState = SplashUiState.Loading, onInputEvent = mockOnInputEvent)
        }

        advanceUntilIdle()

        composeTestRule.onNode(hasTestTag("loadingIndicator"))
            .assertIsDisplayed()
    }

    @Test
    fun `should show error state`() = runTest {
        every { mockOnInputEvent.invoke(any()) } returns Unit

        composeTestRule.setContent {
            SplashView().ViewContent(uiState = SplashUiState.Error, onInputEvent = mockOnInputEvent)
        }

        advanceUntilIdle()

        composeTestRule.onNode(hasTestTag("errorMessage"))
            .assertIsDisplayed()

        composeTestRule.onNode(hasText(i18n.tr("Try again")))
            .performClick()

        verify { mockOnInputEvent.invoke(SplashInputEvent.Load) }
    }
}
