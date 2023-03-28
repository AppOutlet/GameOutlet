package appoutlet.gameoutlet.repository.deals.api

data class DealResponse(
    val internalName: String,
    val title: String,
    val metacriticLink: String?,
    val metacriticScore: String?,
    val dealID: String,
    val storeID: String,
    val gameID: String,
    val salePrice: String,
    val normalPrice: String,
    val isOnSale: String,
    val savings: String,
    val steamRatingText: String?,
    val steamRatingPercent: String?,
    val steamRatingCount: String?,
    val steamAppID: String?,
    val releaseDate: Long,
    val lastChange: Long,
    val dealRating: String?,
    val thumb: String,
)
