package appoutlet.gameoutlet.feature.about

import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface AboutInputEvent : InputEvent {
    object Load : AboutInputEvent
    data class OpenLink(val url: String) : AboutInputEvent
}
