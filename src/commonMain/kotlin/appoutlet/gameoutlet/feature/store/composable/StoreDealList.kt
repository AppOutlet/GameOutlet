package appoutlet.gameoutlet.feature.store.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.feature.store.DealViewData
import appoutlet.gameoutlet.feature.store.StoreInputEvent
import appoutlet.gameoutlet.feature.store.StoreViewData

@Composable
fun StoreDealList(
    viewData: StoreViewData,
    onInputEvent: (StoreInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(viewData.deals) { deal ->
            Deal(
                modifier = Modifier.fillMaxWidth(),
                viewData = deal,
                onInputEvent = onInputEvent,
            )
        }
    }
}

@Composable
private fun Deal(
    viewData: DealViewData,
    onInputEvent: (StoreInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(viewData.title)
}