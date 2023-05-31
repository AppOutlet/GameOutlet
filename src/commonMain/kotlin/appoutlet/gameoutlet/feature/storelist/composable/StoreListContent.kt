package appoutlet.gameoutlet.feature.storelist.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.composable.Error
import appoutlet.gameoutlet.feature.common.composable.Loading
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import appoutlet.gameoutlet.feature.storelist.StoreListInputEvent
import appoutlet.gameoutlet.feature.storelist.StoreListUiState

@Composable
fun StoreListContent(
    uiState: StoreListUiState,
    onInputEvent: (StoreListInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ScreenTitle(text = i18n.tr("Stores"))
        when (uiState) {
            StoreListUiState.Idle -> onInputEvent(StoreListInputEvent.Load)
            StoreListUiState.Error -> Error(
                modifier = Modifier.fillMaxSize(),
                message = i18n.tr("We could not get the list of Stores"),
                onTryAgain = { onInputEvent(StoreListInputEvent.Load) }
            )

            StoreListUiState.Loading -> Loading(
                modifier = Modifier.fillMaxSize(),
                text = i18n.tr("We are fetching the list of stores")
            )

            is StoreListUiState.Loaded -> StoreList(
                uiState = uiState,
                onInputEvent = onInputEvent,
            )
        }
    }
}
