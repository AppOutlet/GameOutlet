package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface SplashInputEvent : InputEvent {
    object Load : SplashInputEvent
}
