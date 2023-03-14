package appoutlet.gameoutlet.feature.splash

import appoutlet.gameoutlet.feature.util.InputEvent

sealed interface SplashInputEvent : InputEvent {
    object Load : SplashInputEvent
}
