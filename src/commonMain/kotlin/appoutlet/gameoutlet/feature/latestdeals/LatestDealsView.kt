package appoutlet.gameoutlet.feature.latestdeals

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.common.composable.Error
import appoutlet.gameoutlet.feature.common.composable.Loading
import appoutlet.gameoutlet.feature.latestdeals.composable.LatestDealsItems
import org.koin.core.component.inject

class LatestDealsView : View<LatestDealsUiState, LatestDealsInputEvent>() {
    override val viewModel by inject<LatestDealsViewModel>()

    @Composable
    override fun ViewContent(uiState: LatestDealsUiState, onInputEvent: (LatestDealsInputEvent) -> Unit) {
        LatestDealsViewContent(uiState, onInputEvent)
    }
}

@Composable
private fun LatestDealsViewContent(uiState: LatestDealsUiState, onInputEvent: (LatestDealsInputEvent) -> Unit) {
    when (uiState) {
        LatestDealsUiState.Idle -> onInputEvent(LatestDealsInputEvent.Load)

        LatestDealsUiState.Error -> Error(
            modifier = Modifier.fillMaxSize(),
            message = i18n.tr("We were unable to get the latest deals"),
            onTryAgain = { onInputEvent(LatestDealsInputEvent.Load) },
        )

        LatestDealsUiState.Loading -> Loading(text = i18n.tr("Fetching the latest deals for you"))

        is LatestDealsUiState.Loaded -> LatestDealsItems(deals = uiState.uiModels, onInputEvent = onInputEvent)
    }
}

@Composable
@Preview
private fun LatestDealsViewContentPreview() {
    GameOutletTheme {
        LatestDealsViewContent(LatestDealsUiState.Idle) {}
    }
}
