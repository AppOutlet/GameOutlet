package appoutlet.gameoutlet.feature.latestdeals

import appoutlet.gameoutlet.feature.common.UiState
import appoutlet.gameoutlet.feature.latestdeals.composable.DealUiModel

sealed interface LatestDealsUiState : UiState {
    object Idle : LatestDealsUiState
    object Error : LatestDealsUiState
    object Loading : LatestDealsUiState

    class Loaded(val uiModels: List<DealUiModel>) : LatestDealsUiState
}
