package appoutlet.gameoutlet.feature.settings

import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Theme
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import name.kropp.kotlinx.gettext.I18n
import kotlin.test.Test

class SettingsViewDataMapperTest : UnitTest<SettingsViewDataMapper>() {
    private val mockI18n = mockk<I18n>()

    override fun buildSut() = SettingsViewDataMapper(mockI18n)

    @Test
    fun `should map theme view data - LIGHT`() {
        every { mockI18n.tr("Light") } returns "Light"
        every { mockI18n.tr("Dark") } returns "Dark"
        every { mockI18n.tr("System default") } returns "System default"

        val actual = sut.invoke(Theme.LIGHT)

        with(actual.themeViewData) {
            assertThat(lightButton.name).isEqualTo("Light")
            assertThat(lightButton.isSelected).isTrue()
            assertThat(lightButton.inputEvent).isEqualTo(SettingsInputEvent.UpdateThemePreference(Theme.LIGHT))

            assertThat(darkButton.isSelected).isFalse()
            assertThat(systemThemeButton.isSelected).isFalse()
        }
    }

    @Test
    fun `should map theme view data - DARK`() {
        every { mockI18n.tr("Light") } returns "Light"
        every { mockI18n.tr("Dark") } returns "Dark"
        every { mockI18n.tr("System default") } returns "System default"

        val actual = sut.invoke(Theme.DARK)

        with(actual.themeViewData) {
            assertThat(darkButton.name).isEqualTo("Dark")
            assertThat(darkButton.isSelected).isTrue()
            assertThat(darkButton.inputEvent).isEqualTo(SettingsInputEvent.UpdateThemePreference(Theme.DARK))

            assertThat(lightButton.isSelected).isFalse()
            assertThat(systemThemeButton.isSelected).isFalse()
        }
    }

    @Test
    fun `should map theme view data - SYSTEM`() {
        every { mockI18n.tr("Light") } returns "Light"
        every { mockI18n.tr("Dark") } returns "Dark"
        every { mockI18n.tr("System default") } returns "System default"

        val actual = sut.invoke(Theme.SYSTEM)

        with(actual.themeViewData) {
            assertThat(systemThemeButton.name).isEqualTo("System default")
            assertThat(systemThemeButton.isSelected).isTrue()
            assertThat(systemThemeButton.inputEvent).isEqualTo(SettingsInputEvent.UpdateThemePreference(Theme.SYSTEM))

            assertThat(lightButton.isSelected).isFalse()
            assertThat(darkButton.isSelected).isFalse()
        }
    }
}
