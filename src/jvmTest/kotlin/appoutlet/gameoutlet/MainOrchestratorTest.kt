package appoutlet.gameoutlet

import appoutlet.gameoutlet.domain.Theme
import appoutlet.gameoutlet.repository.theme.ThemeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MainOrchestratorTest {
    @Test
    fun `observe theme - light`() = runTest {
        val mockThemeRepository = mockk<ThemeRepository>()

        every { mockThemeRepository.getTheme() } returns Theme.SYSTEM

        every { mockThemeRepository.observeTheme(any()) } answers {
            (firstArg() as ((Theme) -> Unit))(Theme.LIGHT)
        }

        val subject = MainOrchestrator(mockThemeRepository)

        val actual = subject.isDarkTheme(true)
            .first()

        assertThat(actual).isFalse()
    }

    @Test
    fun `observe theme - dark`() = runTest {
        val mockThemeRepository = mockk<ThemeRepository>()

        every { mockThemeRepository.getTheme() } returns Theme.SYSTEM

        every { mockThemeRepository.observeTheme(any()) } answers {
            (firstArg() as ((Theme) -> Unit))(Theme.DARK)
        }

        val subject = MainOrchestrator(mockThemeRepository)

        val actual = subject.isDarkTheme(false)
            .first()

        assertThat(actual).isTrue()
    }

    @Test
    fun `observe theme - system`() = runTest {
        val mockThemeRepository = mockk<ThemeRepository>()

        every { mockThemeRepository.getTheme() } returns Theme.LIGHT

        every { mockThemeRepository.observeTheme(any()) } answers {
            (firstArg() as ((Theme) -> Unit))(Theme.SYSTEM)
        }

        val subject = MainOrchestrator(mockThemeRepository)

        val actual = subject.isDarkTheme(true)
            .first()

        assertThat(actual).isTrue()
    }
}
