package appoutlet.gameoutlet.feature.latestdeals

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.latestdeals.composable.Deal
import org.koin.core.component.inject

class LatestDealsView : View<LatestDealsUiState, LatestDealsInputEvent>() {
    override val viewModel by inject<LatestDealsViewModel>()
    override val initialState = LatestDealsUiState.Idle

    @Composable
    override fun ViewContent(uiState: LatestDealsUiState, onInputEvent: (LatestDealsInputEvent) -> Unit) {
        LatestDealsViewContent(uiState, onInputEvent)
    }
}

@Composable
private fun LatestDealsViewContent(uiState: LatestDealsUiState, onInputEvent: (LatestDealsInputEvent) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Adaptive(250.dp)) {
        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            Text(
                modifier = Modifier.semantics { testTag = "screenTitle" }.padding(MaterialTheme.spacing.small),
                text = i18n.tr("Latest deals"),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            Text("Not find what you are looking for? Search")
        }
    }
}


@Composable
@Preview
private fun LatestDealsViewContentPreview() {
    GameOutletTheme {
        LatestDealsViewContent(LatestDealsUiState.Idle) {}
    }
}
