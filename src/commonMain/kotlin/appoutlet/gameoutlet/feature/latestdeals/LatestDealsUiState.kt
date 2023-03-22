package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.feature.common.UiState

sealed interface LatestDealsUiState : UiState {
    object Idle : LatestDealsUiState
}
