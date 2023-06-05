package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface StoreInputEvent : InputEvent {
    data class Load(val store: Store) : StoreInputEvent
    object NavigateBack : StoreInputEvent
    data class SelectDeal(val deal: Deal) : StoreInputEvent
}
