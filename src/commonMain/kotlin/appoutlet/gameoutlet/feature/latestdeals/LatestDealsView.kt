package appoutlet.gameoutlet.feature.latestdeals

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.util.View
import org.koin.core.component.inject

class LatestDealsView : View<LatestDealsUiState, LatestDealsInputEvent>() {
    override val viewModel by inject<LatestDealsViewModel>()
    override val initialState = LatestDealsUiState.Idle

    @Composable
    override fun ViewContent(uiState: LatestDealsUiState, onInputEvent: (LatestDealsInputEvent) -> Unit) {
        Text(i18n.tr("Latest deals"))
    }
}
