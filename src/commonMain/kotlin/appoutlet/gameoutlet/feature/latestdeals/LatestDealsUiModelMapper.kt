package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.feature.latestdeals.composable.DealStoreUiModel
import appoutlet.gameoutlet.feature.latestdeals.composable.DealUiModel
import name.kropp.kotlinx.gettext.I18n
import org.javamoney.moneta.Money
import org.javamoney.moneta.format.CurrencyStyle
import java.util.Locale
import javax.money.format.AmountFormatQueryBuilder
import javax.money.format.MonetaryFormats

class LatestDealsUiModelMapper(
    private val i18n: I18n,
) {
    private val amountFormatQuery = AmountFormatQueryBuilder.of(Locale.US).set(CurrencyStyle.SYMBOL).build()
    private val amountFormat = MonetaryFormats.getAmountFormat(amountFormatQuery)

    operator fun invoke(deals: List<Deal>): List<DealUiModel> {
        val dealsMap = deals.groupBy { it.game }
        return dealsMap.map { (game, deals) ->
            mapDealUiModel(game, deals)
        }
    }

    private fun mapDealUiModel(game: Game, deals: List<Deal>): DealUiModel {
        val cheapestDeal = deals.minBy { it.salePrice }

        return DealUiModel(
            gameTitle = game.title,
            gameId = game.id,
            gameImage = game.image,
            currentPrice = formatMoney(cheapestDeal.salePrice),
            oldPrice = formatMoney(cheapestDeal.normalPrice),
            stores = mapStore(deals)
        )
    }

    private fun formatMoney(money: Money): String {
        return if (money.isZero) {
            i18n.tr("FREE")
        } else {
            amountFormat.format(money)
        }
    }

    private fun mapStore(deals: List<Deal>) = deals.mapNotNull { deal ->
        val logoUrl = deal.store.iconUrl
        logoUrl?.let { logo ->
            DealStoreUiModel(
                icon = logo,
                name = deal.store.name,
            )
        }
    }
}
