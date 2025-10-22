package appoutlet.gameoutlet.feature.store

import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.common.InputEvent
import appoutlet.gameoutlet.feature.game.GameNavArgs

sealed interface StoreInputEvent : InputEvent {
    data class Load(val store: Store) : StoreInputEvent
    object NavigateBack : StoreInputEvent
    data class SelectDeal(val gameNavArgs: GameNavArgs) : StoreInputEvent
}
