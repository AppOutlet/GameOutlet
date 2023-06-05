package appoutlet.gameoutlet.feature.common.util

import appoutlet.gameoutlet.core.translation.i18n
import java.util.Locale
import javax.money.format.AmountFormatQueryBuilder
import javax.money.format.MonetaryFormats
import org.javamoney.moneta.Money
import org.javamoney.moneta.format.CurrencyStyle

private val amountFormatQuery = AmountFormatQueryBuilder.of(Locale.US)
    .set(CurrencyStyle.SYMBOL)
    .build()

private val amountFormat = MonetaryFormats.getAmountFormat(amountFormatQuery)

fun Money.asString(): String {
    return if (this.isZero) {
        i18n.tr("FREE")
    } else {
        amountFormat.format(this)
    }
}