package appoutlet.gameoutlet.domain

import org.javamoney.moneta.Money
import java.time.LocalDateTime

data class Deal(
    val id: String,
    val game: Game,
    val store: Store,
    val salePrice: Money,
    val normalPrice: Money,
    val savings: Float,
    val releaseDate: LocalDateTime,
    val lastChange: LocalDateTime,
    val rating: Float,
)
