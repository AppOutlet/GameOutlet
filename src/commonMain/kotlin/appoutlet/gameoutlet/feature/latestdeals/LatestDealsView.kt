package appoutlet.gameoutlet.feature.latestdeals

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import org.koin.core.component.inject

class LatestDealsView : View<LatestDealsUiState, LatestDealsInputEvent>() {
    override val viewModel by inject<LatestDealsViewModel>()
    override val initialState = LatestDealsUiState.Idle

    @Composable
    override fun ViewContent(uiState: LatestDealsUiState, onInputEvent: (LatestDealsInputEvent) -> Unit) {
        Text(
            modifier = Modifier.semantics { testTag = "screenTitle" },
            text = i18n.tr("Latest deals"),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
