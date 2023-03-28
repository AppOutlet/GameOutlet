package appoutlet.gameoutlet.feature.settings

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import org.koin.core.component.inject

class SettingsView : View<SettingsUiState, SettingsInputEvent>() {
    override val viewModel by inject<SettingsViewModel>()

    @Composable
    override fun ViewContent(uiState: SettingsUiState, onInputEvent: (SettingsInputEvent) -> Unit) {
        Text(
            modifier = Modifier.semantics { testTag = "screenTitle" },
            text = i18n.tr("Settings"),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
