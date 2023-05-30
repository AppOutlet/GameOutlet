package appoutlet.gameoutlet.feature.storelist.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.feature.storelist.StoreListInputEvent
import appoutlet.gameoutlet.feature.storelist.StoreListUiState
import appoutlet.gameoutlet.feature.storelist.StoreUiModel

@Composable
fun StoreList(
    uiState: StoreListUiState.Loaded,
    onInputEvent: (StoreListInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(uiState.stores) { store ->
            Store(
                uiModel = store,
                onInputEvent = onInputEvent
            )
        }
    }
}

@Composable
private fun Store(
    uiModel: StoreUiModel,
    onInputEvent: (StoreListInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.widthIn(maxOf(512.dp))) {
        Row(modifier.fillMaxWidth()) {
            Text(uiModel.name)
        }
    }
}