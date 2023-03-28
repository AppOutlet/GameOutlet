package appoutlet.gameoutlet.feature.latestdeals.composable

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import appoutlet.gameoutlet.feature.latestdeals.LatestDealsInputEvent

@Composable
fun LatestDealsItems(deals: List<DealUiModel>, onInputEvent: (LatestDealsInputEvent) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Adaptive(250.dp)) {
        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            ScreenTitle(i18n.tr("Latest deals"))
        }

        items(deals) { deal ->
            Deal(deal)
        }

        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            Text("Not find what you are looking for? Search")
        }
    }
}