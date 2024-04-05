package appoutlet.gameoutlet.core.util

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockkStatic
import java.time.Instant
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
        val fixtureNow = Instant.now()
        val fixtureNowEpochMillis = fixtureNow.toEpochMilli()
        val fixtureLocalDateTime = LocalDateTime.ofInstant(fixtureNow, ZoneOffset.systemDefault())

        val actual = sut.fromEpochMillis(fixtureNowEpochMillis)

        assertThat(actual.getMillis()).isEqualTo(fixtureLocalDateTime.getMillis())
    }

    private fun LocalDateTime.getMillis() = atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli()
}
