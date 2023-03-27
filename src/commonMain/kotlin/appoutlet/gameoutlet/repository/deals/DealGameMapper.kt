package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.repository.deals.api.DealResponse

class DealGameMapper {
    operator fun invoke(dealResponse: DealResponse): Game? {
        val id = dealResponse.gameID.toLongOrNull()

        return id?.let { gameId ->
            Game(
                id = gameId,
                title = dealResponse.title,
                image = dealResponse.thumb,
                metacritic = null,
                steam = null,
            )
        }
    }
}