package appoutlet.gameoutlet.feature.store.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.composable.Error
import appoutlet.gameoutlet.feature.common.composable.Loading
import appoutlet.gameoutlet.feature.store.StoreInputEvent
import appoutlet.gameoutlet.feature.store.StoreUiState

@Composable
fun StoreContent(
    storeName: String,
    uiState: StoreUiState,
    onInputEvent: (StoreInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        StoreTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            storeName = storeName,
            onInputEvent = onInputEvent
        )

        when (uiState) {
            StoreUiState.Idle -> onInputEvent(StoreInputEvent.Load)

            StoreUiState.Error -> Error(
                modifier = Modifier.fillMaxSize(),
                message = i18n.tr(
                    "We could not get the list of deals from {{storeName}}",
                    "storeName" to storeName
                ),
                onTryAgain = { onInputEvent(StoreInputEvent.Load) }
            )

            StoreUiState.Loading -> Loading(
                modifier = Modifier.fillMaxSize(),
                text = i18n.tr(
                    "We are fetching the list of deals from {{storeName}}",
                    "storeName" to storeName
                )
            )

            is StoreUiState.Loaded -> StoreDealList(
                modifier = Modifier.fillMaxWidth(),
                viewData = uiState.viewData,
                onInputEvent = onInputEvent
            )
        }
    }
}
