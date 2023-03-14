package gameoutlet.repository.store.api.model

data class StoreResponse(
    val storeID: String,
    val storeName: String,
    val isActive: Int,
    val images: StoreImagesResponse,
)
