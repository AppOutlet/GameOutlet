package appoutlet.gameoutlet.core.util

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockkStatic
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.test.Test

class TimeProviderTest {

    private val sut = TimeProvider()

    @Test
    fun now() {
        val fixtureNow = LocalDateTime.now()

        mockkStatic(LocalDateTime::class)

        every { LocalDateTime.now() } returns fixtureNow

        assertThat(sut.now()).isEqualTo(fixtureNow)
    }

    @Test
    fun fromEpochMillis() {
        val fixtureNow = LocalDateTime.now()

        val actual = sut.fromEpochMillis(fixtureNow.toInstant(ZoneOffset.UTC).toEpochMilli())

        assertThat(
            actual.toInstant(ZoneOffset.UTC).toEpochMilli()
        ).isEqualTo(fixtureNow.toInstant(ZoneOffset.UTC).toEpochMilli())
    }
}
