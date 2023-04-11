package appoutlet.gameoutlet.repository.deals.api

data class GameResponse(
    val info: GameInfoResponse,
    val cheapestPriceEver: CheapestPriceEverResponse,
    val deals: List<GameDealResponse>,
)

data class GameInfoResponse(
    val title: String,
    val steamAppId: String?,
    val thumb: String,
)

data class CheapestPriceEverResponse(
    val price: String,
    val date: Long,
)

data class GameDealResponse(
    val storeID: String,
    val dealID: String,
    val price: String,
    val retailPrice: String,
    val savings: String,
)
