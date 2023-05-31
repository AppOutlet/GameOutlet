package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface StoreInputEvent : InputEvent {
    object Load : StoreInputEvent
    data class SelectDeal(val deal: Deal) : StoreInputEvent
}