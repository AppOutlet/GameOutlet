package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.core.util.TimeProvider
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.api.DealResponse
import java.math.BigDecimal
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.javamoney.moneta.Money

class DealMapper(
    private val dealGameMapper: DealGameMapper,
    private val dealStoreMapper: DealStoreMapper,
    private val timeProvider: TimeProvider
) {
    suspend operator fun invoke(dealsResponse: List<DealResponse>) = coroutineScope {
        val deferredDeals = dealsResponse.map { dealsResponse ->
            async { map(dealsResponse) }
        }

        deferredDeals.awaitAll().filterNotNull()
    }

    private fun map(response: DealResponse): Deal? {
        val game = dealGameMapper(response)
        val store = dealStoreMapper(response)

        return if (game != null && store != null) {
            mapDeal(response, game, store)
        } else {
            null
        }
    }

    private fun mapDeal(response: DealResponse, game: Game, store: Store): Deal {
        return Deal(
            id = response.dealID,
            game = game,
            store = store,
            salePrice = response.salePrice.asMoney(),
            normalPrice = response.normalPrice.asMoney(),
            savings = response.savings.toFloatOrNull() ?: 0f,
            releaseDate = timeProvider.fromEpochMillis(response.releaseDate),
            lastChange = timeProvider.fromEpochMillis(response.lastChange),
            rating = response.dealRating?.toFloatOrNull() ?: 0f,
        )
    }

    private fun String.asMoney(): Money {
        return Money.of(BigDecimal(this), "USD")
    }
}
