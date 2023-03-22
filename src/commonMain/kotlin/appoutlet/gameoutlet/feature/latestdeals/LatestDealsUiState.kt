package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.feature.util.UiState

sealed interface LatestDealsUiState : UiState {
    object Idle : LatestDealsUiState
}
