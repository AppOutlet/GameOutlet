package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.repository.deals.api.DealApi
import appoutlet.gameoutlet.repository.deals.api.GameApi

class DealRepository(
    private val dealApi: DealApi,
    private val dealMapper: DealMapper,
    private val gameApi: GameApi,
    private val gameDealMapper: GameDealMapper
) {
    suspend fun findLatestDeals(): List<Deal> {
        val dealsResponse = dealApi.findLatestDeals()
        return dealMapper(dealsResponse)
    }

    suspend fun findDealsByGame(game: Game): List<Deal> {
        val gameResponse = gameApi.findById(game.id)
        return gameResponse.deals.mapNotNull { dealResponse -> gameDealMapper(game, dealResponse) }
    }
}
