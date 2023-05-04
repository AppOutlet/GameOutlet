package appoutlet.gameoutlet.feature.latestdeals.composable

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import appoutlet.gameoutlet.feature.latestdeals.LatestDealsInputEvent
import appoutlet.gameoutlet.feature.latestdeals.LatestDealsUiState

@Composable
fun LatestDealsItems(
    uiState: LatestDealsUiState.Loaded,
    onInputEvent: (LatestDealsInputEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(minSize = 300.dp)) {
        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            ScreenTitle(i18n.tr("Latest deals"))
        }

        items(uiState.uiModels) { deal ->
            Deal(deal = deal, onInputEvent = onInputEvent)
        }

        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            LatestDealsFooter(onInputEvent = onInputEvent)
        }
    }
}
