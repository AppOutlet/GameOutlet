package appoutlet.gameoutlet.feature.about

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import appoutlet.gameoutlet.core.testing.UiTest
import io.mockk.mockk
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.test.Test

class AboutViewTest : UiTest() {

    override val koinTestModule: Module
        get() = module {
            single { mockk<AboutViewModel>(relaxed = true) }
        }

    @Test
    fun `should show about view`() {
        val fixtureUiState = fixture<AboutUiState.Loaded>()

        composeTestRule.setContent { AboutView().ViewContent(fixtureUiState, {}) }

        composeTestRule.onNodeWithText("GameOutlet")
            .assertIsDisplayed()
    }
}
