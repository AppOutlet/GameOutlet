package appoutlet.gameoutlet.feature.home

import appoutlet.gameoutlet.feature.util.InputEvent

sealed interface HomeInputEvent : InputEvent {
    object Load : HomeInputEvent
}
