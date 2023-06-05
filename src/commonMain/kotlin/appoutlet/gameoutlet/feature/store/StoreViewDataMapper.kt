package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.feature.common.util.asString
import appoutlet.gameoutlet.feature.game.GameNavArgs

class StoreViewDataMapper {
    operator fun invoke(deals: List<Deal>): StoreViewData {
        return StoreViewData(
            deals = deals.map(this::mapDealViewData)
        )
    }

    private fun mapDealViewData(deal: Deal): DealViewData {
        val normalPrice = if (deal.salePrice == deal.normalPrice) {
            null
        } else {
            deal.normalPrice.asString()
        }

        return DealViewData(
            title = deal.game.title,
            currentPrice = deal.salePrice.asString(),
            normalPrice = normalPrice,
            image = deal.game.image,
            inputEvent = mapInputEvent(deal.game),
        )
    }

    private fun mapInputEvent(game: Game): StoreInputEvent {
        val gameNavArgs = GameNavArgs(
            gameId = game.id,
            gameTitle = game.title,
            gameImage = game.image,
        )

        return StoreInputEvent.SelectDeal(gameNavArgs)
    }
}