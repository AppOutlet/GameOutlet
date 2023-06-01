package appoutlet.gameoutlet.feature.store

data class StoreViewData(val deals: List<DealViewData>)

data class DealViewData(
    val title: String,
    val currentPrice: String,
    val normalPrice: String,
    val showNormalPrice: Boolean,
    val image: String,
    val inputEvent: StoreInputEvent
)
