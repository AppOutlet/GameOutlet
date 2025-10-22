package appoutlet.gameoutlet.domain

data class Store(
    val id: Int,
    val name: String = "",
    val bannerUrl: String? = null,
    val logoUrl: String? = null,
    val iconUrl: String? = null,
)
