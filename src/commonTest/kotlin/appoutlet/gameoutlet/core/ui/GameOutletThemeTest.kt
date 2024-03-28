package appoutlet.gameoutlet.core.ui

import androidx.compose.material3.MaterialTheme
import appoutlet.gameoutlet.core.testing.UiTest
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class GameOutletThemeTest : UiTest() {
    @Test
    fun `should render light theme`() {
        composeTestRule.setContent {
            GameOutletTheme(
                useDarkTheme = false,
                content = {
                    assertThat(MaterialTheme.colorScheme).isEqualTo(LightColors)
                }
            )
        }
    }

    @Test
    fun `should render dark theme`() {
        composeTestRule.setContent {
            GameOutletTheme(
                useDarkTheme = true,
                content = {
                    assertThat(MaterialTheme.colorScheme).isEqualTo(DarkColors)
                }
            )
        }
    }
}
