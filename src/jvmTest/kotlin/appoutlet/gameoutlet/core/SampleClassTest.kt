package appoutlet.gameoutlet.core

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class SampleClassTest {

    @Test
    fun `should sum`() {
        val result = SampleClass().sum(1, 1)
        assertThat(result).isEqualTo(2)
    }
}
