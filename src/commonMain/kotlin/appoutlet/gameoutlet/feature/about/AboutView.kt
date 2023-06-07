package appoutlet.gameoutlet.feature.about

import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import org.koin.core.component.get

class AboutView : View<AboutUiState, AboutInputEvent>() {
    override val viewModel = get<AboutViewModel>()

    @Composable
    override fun ViewContent(uiState: AboutUiState, onInputEvent: (AboutInputEvent) -> Unit) {
        ScreenTitle(i18n.tr("About"))
    }
}
