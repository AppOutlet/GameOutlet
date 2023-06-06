package appoutlet.gameoutlet.repository.theme

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Theme
import appoutlet.gameoutlet.repository.preference.PreferenceRepository
import appoutlet.gameoutlet.repository.theme.ThemeRepository.Companion.PREFERENCE_THEME
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class ThemeRepositoryTest : UnitTest<ThemeRepository>() {
    private val mockPreferenceRepository = mockk<PreferenceRepository>(relaxUnitFun = true)

    override fun buildSut() = ThemeRepository(mockPreferenceRepository)

    @Test
    fun `should set theme`() {
        val fixtureTheme = fixture<Theme>()

        sut.setTheme(fixtureTheme)

        verify { mockPreferenceRepository.setPreference(PREFERENCE_THEME, fixtureTheme.name) }
    }

    @Test
    fun `should get theme`() {
        val fixtureTheme = fixture<Theme>()

        every { mockPreferenceRepository.getPreference(PREFERENCE_THEME) } returns fixtureTheme.name

        val actual = sut.getTheme()

        assertThat(actual).isEqualTo(fixtureTheme)
    }
}
