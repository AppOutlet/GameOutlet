package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.core.util.TimeProvider
import appoutlet.gameoutlet.core.util.asMoney
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.api.GameDealResponse

class GameDealMapper(private val timeProvider: TimeProvider) {
    operator fun invoke(game: Game,gameDealResponse: GameDealResponse): Deal? {
        val store = mapStore(gameDealResponse.storeID) ?: return null

        return Deal(
            id = gameDealResponse.dealID,
            game = game,
            store = store,
            salePrice = gameDealResponse.price.asMoney(),
            normalPrice = gameDealResponse.retailPrice.asMoney(),
            savings = gameDealResponse.savings.toFloatOrNull() ?: 0f,
            releaseDate = timeProvider.now(),
            lastChange = timeProvider.now(),
            rating = 0f
        )
    }

    private fun mapStore(storeId: String?): Store? {
        return storeId?.toIntOrNull()?.let { Store(it) }
    }
}
