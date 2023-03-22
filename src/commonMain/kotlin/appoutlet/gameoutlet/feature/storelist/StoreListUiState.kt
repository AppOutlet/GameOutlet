package appoutlet.gameoutlet.feature.storelist

import appoutlet.gameoutlet.feature.common.UiState

sealed interface StoreListUiState : UiState {
    object Idle : StoreListUiState
}
