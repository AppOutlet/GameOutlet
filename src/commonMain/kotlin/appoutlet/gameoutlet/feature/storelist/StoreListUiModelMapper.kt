package appoutlet.gameoutlet.feature.storelist

import appoutlet.gameoutlet.domain.Store

class StoreListUiModelMapper {
    operator fun invoke(entities: List<Store>): List<StoreUiModel> {
        return entities.map(this::mapStore)
    }

    private fun mapStore(store: Store) = StoreUiModel(
        icon = store.logoUrl,
        name = store.name,
        inputEvent = StoreListInputEvent.SelectStore(store),
    )
}