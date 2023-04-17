package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.repository.deals.api.GameSearchResponse

class GameMapper {
    operator fun invoke(gameSearchResponse: GameSearchResponse): Game? {
        val gameId = gameSearchResponse.gameID.toLongOrNull() ?: return null
        return Game(
            id = gameId,
            title = gameSearchResponse.external,
            image = gameSearchResponse.thumb
        )
    }
}
