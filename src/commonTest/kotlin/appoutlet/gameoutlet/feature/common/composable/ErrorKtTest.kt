package appoutlet.gameoutlet.feature.common.composable

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import appoutlet.gameoutlet.core.testing.UiTest
import appoutlet.gameoutlet.core.translation.i18n
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class ErrorKtTest : UiTest() {
    private val mockOnTryAgain = mockk<() -> Unit>()

    @Test
    fun `should show error composable - default values`() {
        composeTestRule.setContent {
            Error()
        }

        composeTestRule.onNode(hasTestTag("title"))
            .assertIsDisplayed()
            .assert(hasText(i18n.tr("Something went wrong")))

        composeTestRule.onNode(hasTestTag("message"))
            .assertIsDisplayed()
            .assert(hasText(i18n.tr("An unexpected error occurred")))

        composeTestRule.onNode(hasTestTag("button"))
            .assertDoesNotExist()
    }

    @Test
    fun `should show error composable - custom values`() {
        val titleFixture = fixture<String>()
        val messageFixture = fixture<String>()
        val buttonTextFixture = fixture<String>()

        every { mockOnTryAgain.invoke() } returns Unit

        composeTestRule.setContent {
            Error(
                title = titleFixture,
                message = messageFixture,
                buttonText = buttonTextFixture,
                onTryAgain = mockOnTryAgain
            )
        }

        composeTestRule.onNode(hasTestTag("title"))
            .assertIsDisplayed()
            .assert(hasText(titleFixture))

        composeTestRule.onNode(hasTestTag("message"))
            .assertIsDisplayed()
            .assert(hasText(messageFixture))

        composeTestRule.onNode(hasTestTag("button"))
            .assertIsDisplayed()
            .assert(hasText(buttonTextFixture))
            .performClick()

        verify(exactly = 1) { mockOnTryAgain.invoke() }
    }
}
