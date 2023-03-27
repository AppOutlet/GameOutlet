package appoutlet.gameoutlet.repository.deals.api

/**
 *       "internalName": "ONEPIECEBURNINGBLOODGOLDEDITION",
 *         "title": "ONE PIECE BURNING BLOOD GOLD EDITION",
 *         "metacriticLink": "/game/pc/one-piece-burning-blood---gold-edition",
 *         "dealID": "IZE0Ktezh1S5r0%2BqSsR5WLWa1iGWK1%2ByLqV5ThFmzUM%3D",
 *         "storeID": "23",
 *         "gameID": "157072",
 *         "salePrice": "7.50",
 *         "normalPrice": "74.99",
 *         "isOnSale": "1",
 *         "savings": "89.998666",
 *         "metacriticScore": "0",
 *         "steamRatingText": null,
 *         "steamRatingPercent": "0",
 *         "steamRatingCount": "0",
 *         "steamAppID": null,
 *         "releaseDate": 1472688000,
 *         "lastChange": 1679828032,
 *         "dealRating": "10.0",
 *         "thumb": "https://gamersgatep.imgix.net/a/3/4/026d064cc7e1fb721f497398a3435dfcfbe0c43a.jpg?auto=&w="
 *
 * */
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
