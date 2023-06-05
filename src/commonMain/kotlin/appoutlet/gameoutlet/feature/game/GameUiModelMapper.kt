package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.common.util.asString
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import name.kropp.kotlinx.gettext.I18n

class GameUiModelMapper(
    private val i18n: I18n,
) {
    suspend operator fun invoke(
        game: Game,
        deals: List<Deal>,
        isGameSaved: Boolean,
        shouldShowSnackbar: Boolean
    ): GameUiModel {
        return GameUiModel(
            title = game.title,
            image = game.image,
            deals = mapDeals(deals),
            favouriteButton = mapFavouriteButton(game, isGameSaved),
            snackBarMessage = mapSnackBarMessage(isGameSaved, shouldShowSnackbar)
        )
    }

    private fun mapSnackBarMessage(gameSaved: Boolean, shouldShowSnackbar: Boolean): String? {
        if (!shouldShowSnackbar) return null

        return if (gameSaved) {
            i18n.tr("The game was added to the favorites list")
        } else {
            i18n.tr("The game was removed from the favorites list")
        }
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
                    salePrice = deal.salePrice.asString(),
                    normalPrice = deal.normalPrice.asString(),
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
