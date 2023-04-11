package appoutlet.gameoutlet.core.util

import org.javamoney.moneta.Money
import java.math.BigDecimal

fun String.asMoney(): Money {
    return Money.of(BigDecimal(this), "USD")
}
