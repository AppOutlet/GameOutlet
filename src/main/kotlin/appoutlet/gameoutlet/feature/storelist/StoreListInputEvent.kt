package appoutlet.gameoutlet.feature.storelist

import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface StoreListInputEvent : InputEvent {
    object Load : StoreListInputEvent
    data class SelectStore(val store: Store) : StoreListInputEvent
}
