package appoutlet.gameoutlet.repository.store

import appoutlet.gameoutlet.core.database.StoreEntity
import appoutlet.gameoutlet.domain.Store

class StoreEntityMapper {
    operator fun invoke(store: Store) = StoreEntity(
        id = store.id.toLong(),
        name = store.name,
        bannerUrl = store.bannerUrl,
        logoUrl = store.logoUrl,
        iconUrl = store.iconUrl,
    )
}
