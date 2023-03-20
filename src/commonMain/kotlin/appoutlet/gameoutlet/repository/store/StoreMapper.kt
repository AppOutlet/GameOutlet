package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.core.database.StoreEntity
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.store.api.model.StoreResponse

class StoreMapper {
    operator fun invoke(storeEntity: StoreEntity) = Store(
        id = storeEntity.id.toInt(),
        name = storeEntity.name,
        bannerUrl = storeEntity.bannerUrl,
        logoUrl = storeEntity.logoUrl,
        iconUrl = storeEntity.iconUrl,
    )

    operator fun invoke(storeResponse: StoreResponse) = Store(
        id = storeResponse.storeID.toInt(),
        name = storeResponse.storeName,
        bannerUrl = parseStoreImage(storeResponse.images.banner),
        logoUrl = parseStoreImage(storeResponse.images.logo),
        iconUrl = parseStoreImage(storeResponse.images.icon),
    )

    private fun parseStoreImage(uri: String) = CHEAP_SHARK_IMAGE_BASE_URL + uri

    companion object {
        const val CHEAP_SHARK_IMAGE_BASE_URL = "https://www.cheapshark.com"
    }
}
