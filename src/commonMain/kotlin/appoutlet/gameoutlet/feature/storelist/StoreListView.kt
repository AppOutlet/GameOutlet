package appoutlet.gameoutlet.feature.storelist

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import org.koin.core.component.inject

class StoreListView : View<StoreListUiState, StoreListInputEvent>() {
    override val viewModel by inject<StoreListViewModel>()

    @Composable
    override fun ViewContent(uiState: StoreListUiState, onInputEvent: (StoreListInputEvent) -> Unit) {
        Text(
            modifier = Modifier.semantics { testTag = "screenTitle" },
            text = i18n.tr("Stores"),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
