package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.feature.common.InputEvent

sealed interface LatestDealsInputEvent : InputEvent {
    object Load : LatestDealsInputEvent
    object ToSearch : LatestDealsInputEvent
    data class DealClicked(val gameId: Long) : LatestDealsInputEvent
}
