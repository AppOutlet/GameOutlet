package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.feature.common.InputEvent
import appoutlet.gameoutlet.feature.latestdeals.composable.DealUiModel

sealed interface LatestDealsInputEvent : InputEvent {
    object Load : LatestDealsInputEvent
    object ToSearch : LatestDealsInputEvent
    data class DealClicked(val deal: DealUiModel) : LatestDealsInputEvent
}
