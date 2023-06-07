package appoutlet.gameoutlet.feature.about

import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface AboutInputEvent : InputEvent {
   object GoToContribution : AboutInputEvent
}
