package appoutlet.gameoutlet.core.util

import java.math.BigDecimal
import org.javamoney.moneta.Money

fun String.asMoney(): Money {
    return Money.of(BigDecimal(this), "USD")
}
