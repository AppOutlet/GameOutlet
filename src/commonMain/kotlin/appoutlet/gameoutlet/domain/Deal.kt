package appoutlet.gameoutlet.domain

import java.time.LocalDateTime

data class Deal(
    val id: String,
    val game: Game,
    val store: Store,
    val salePrice: Float,
    val normalPrice: Float,
    val savings: Float,
    val releaseDate: LocalDateTime,
    val lastChange: LocalDateTime,
    val rating: Float,
)
