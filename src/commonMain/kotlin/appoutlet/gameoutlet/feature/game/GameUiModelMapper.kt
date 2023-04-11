package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.javamoney.moneta.format.CurrencyStyle
import java.util.Locale
import javax.money.format.AmountFormatQueryBuilder
import javax.money.format.MonetaryFormats

class GameUiModelMapper {
    private val amountFormatQuery = AmountFormatQueryBuilder.of(Locale.US).set(CurrencyStyle.SYMBOL).build()
    private val amountFormat = MonetaryFormats.getAmountFormat(amountFormatQuery)

    suspend operator fun invoke(game: Game, deals: List<Deal>): GameUiModel {
        return GameUiModel(
            title = game.title,
            image = game.image,
            deals = mapDeals(deals)
        )
    }

    private suspend fun mapDeals(deals: List<Deal>): List<GameDealUiModel> = coroutineScope {
        val deferredDealUiModels = deals.map { deal ->
            val samePrice = deal.salePrice == deal.normalPrice
            async {
                GameDealUiModel(
                    id = deal.id,
                    salePrice = amountFormat.format(deal.salePrice),
                    normalPrice = amountFormat.format(deal.normalPrice),
                    showNormalPrice = !samePrice,
                    store = mapStore(deal.store),
                )
            }
        }

        deferredDealUiModels.awaitAll()
    }

    private fun mapStore(store: Store): GameDealStoreUiModel {
        return GameDealStoreUiModel(
            name = store.name,
            icon = store.logoUrl ?: ""
        )
    }
}
