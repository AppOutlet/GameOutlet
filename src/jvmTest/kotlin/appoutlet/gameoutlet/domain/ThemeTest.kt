package appoutlet.gameoutlet.domain

import appoutlet.gameoutlet.core.testing.BaseTest
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class ThemeTest : BaseTest() {
    @Test
    fun `should map theme from string - LIGHT`() {
        val actual = Theme.fromString("LIGHT")

        assertThat(actual).isEqualTo(Theme.LIGHT)
    }

    @Test
    fun `should map theme from string - DARK`() {
        val actual = Theme.fromString("DARK")

        assertThat(actual).isEqualTo(Theme.DARK)
    }

    @Test
    fun `should map theme from string - SYSTEM`() {
        val actual = Theme.fromString("SYSTEM")

        assertThat(actual).isEqualTo(Theme.SYSTEM)
    }

    @Test
    fun `should map theme from string - null`() {
        val actual = Theme.fromString(null)

        assertThat(actual).isEqualTo(Theme.SYSTEM)
    }

    @Test
    fun `should map theme from string - random string`() {
        val actual = Theme.fromString(fixture<String>())

        assertThat(actual).isEqualTo(Theme.SYSTEM)
    }
}
