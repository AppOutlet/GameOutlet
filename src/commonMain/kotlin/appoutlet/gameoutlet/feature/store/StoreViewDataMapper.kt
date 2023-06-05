package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.feature.common.util.asString

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
            inputEvent = StoreInputEvent.SelectDeal(deal),
        )
    }
}