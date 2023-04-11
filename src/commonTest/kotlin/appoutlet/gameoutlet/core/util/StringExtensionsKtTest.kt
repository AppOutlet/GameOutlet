package appoutlet.gameoutlet.core.util

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test
import org.javamoney.moneta.Money


class StringExtensionsKtTest {
    @Test
    fun `should parse string to money`() {
        val actual = "1".asMoney()
        assertThat(actual).isInstanceOf(Money::class.java)
        assertThat(actual.number.intValueExact()).isEqualTo(1)
    }
}
