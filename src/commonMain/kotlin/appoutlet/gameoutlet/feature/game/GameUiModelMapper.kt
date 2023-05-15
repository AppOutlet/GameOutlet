package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import java.util.Locale
import javax.money.format.AmountFormatQueryBuilder
import javax.money.format.MonetaryFormats
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import name.kropp.kotlinx.gettext.I18n
import org.javamoney.moneta.Money
import org.javamoney.moneta.format.CurrencyStyle

class GameUiModelMapper(
    private val i18n: I18n,
) {
    private val amountFormatQuery =
        AmountFormatQueryBuilder.of(Locale.US).set(CurrencyStyle.SYMBOL).build()
    private val amountFormat = MonetaryFormats.getAmountFormat(amountFormatQuery)

    suspend operator fun invoke(
        game: Game,
        deals: List<Deal>,
        isGameSaved: Boolean
    ): GameUiModel {
        return GameUiModel(
            title = game.title,
            image = game.image,
            deals = mapDeals(deals),
            favouriteButton = mapFavouriteButton(game, isGameSaved)
        )
    }

    private fun mapFavouriteButton(game: Game, isGameSaved: Boolean): GameFavouriteButton {
        return GameFavouriteButton(
            isSaved = isGameSaved,
            inputEvent = if (isGameSaved) {
                GameInputEvent.RemoveGameFromFavorites(game)
            } else {
                GameInputEvent.SaveGame(game)
            }
        )
    }

    private suspend fun mapDeals(deals: List<Deal>): List<GameDealUiModel> = coroutineScope {
        val deferredDealUiModels = deals.map { deal ->
            val samePrice = deal.salePrice == deal.normalPrice
            async {
                GameDealUiModel(
                    id = deal.id,
                    salePrice = formatMoney(deal.salePrice),
                    normalPrice = formatMoney(deal.normalPrice),
                    showNormalPrice = !samePrice,
                    store = mapStore(deal.store),
                )
            }
        }

        deferredDealUiModels.awaitAll()
    }

    private fun formatMoney(money: Money): String {
        return if (money.isZero) {
            i18n.tr("FREE")
        } else {
            amountFormat.format(money)
        }
    }

    private fun mapStore(store: Store): GameDealStoreUiModel {
        return GameDealStoreUiModel(
            name = store.name,
            icon = store.logoUrl ?: ""
        )
    }
}
