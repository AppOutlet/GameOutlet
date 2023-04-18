package appoutlet.gameoutlet.repository.deals.api

data class GameSearchResponse(
    val gameID: String,
    val steamAppID: String?,
    val cheapest: String,
    val cheapestDealID: String,
    val external: String,
    val internalName: String,
    val thumb: String,
)
