package appoutlet.gameoutlet.core.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class TimeProvider {
    fun now(): LocalDateTime = LocalDateTime.now()
    fun fromEpochMillis(millis: Long): LocalDateTime {
        val instant = Instant.ofEpochMilli(millis)
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }
}
