package appoutlet.gameoutlet.core.testing

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

abstract class UiTest : BaseTest() {
    @get:Rule
    val composeTestRule = createComposeRule()
}
