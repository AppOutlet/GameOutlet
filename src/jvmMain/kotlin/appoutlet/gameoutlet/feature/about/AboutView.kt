package appoutlet.gameoutlet.feature.about

import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.feature.about.composable.AboutScreen
import appoutlet.gameoutlet.feature.common.View
import org.koin.core.component.get

class AboutView : View<AboutUiState, AboutInputEvent>() {
    override val viewModel = get<AboutViewModel>()

    @Composable
    override fun ViewContent(uiState: AboutUiState, onInputEvent: (AboutInputEvent) -> Unit) {
        AboutScreen(uiState, onInputEvent)
    }
}
