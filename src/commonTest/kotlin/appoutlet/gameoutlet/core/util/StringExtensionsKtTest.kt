package appoutlet.gameoutlet.core.util

import com.google.common.truth.Truth.assertThat
import org.javamoney.moneta.Money
import kotlin.test.Test

class StringExtensionsKtTest {
    @Test
    fun `should parse string to money`() {
        val actual = "1".asMoney()
        assertThat(actual).isInstanceOf(Money::class.java)
        assertThat(actual.number.intValueExact()).isEqualTo(1)
    }
}
