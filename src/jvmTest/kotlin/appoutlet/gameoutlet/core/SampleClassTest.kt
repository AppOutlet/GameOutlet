package appoutlet.gameoutlet.core

import com.google.common.truth.Truth.assertWithMessage
import kotlin.test.Test

class SampleClassTest {
    @Test
    fun `should sum`() {
        val result = SampleClass().sum(1, 1)
        assertWithMessage("SOme messag").that(result).isEqualTo(2)
    }
}
