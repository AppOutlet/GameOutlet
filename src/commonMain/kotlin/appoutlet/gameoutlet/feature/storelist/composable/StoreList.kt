package appoutlet.gameoutlet.feature.storelist.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.feature.storelist.StoreListInputEvent
import appoutlet.gameoutlet.feature.storelist.StoreListUiState

@Composable
fun StoreList(
    uiState: StoreListUiState.Loaded,
    onInputEvent: (StoreListInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {

}