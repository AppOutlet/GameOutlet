package appoutlet.gameoutlet.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.feature.util.View
import org.koin.core.component.inject

class HomeView : View<HomeUiState, HomeInputEvent>() {
    override val viewModel by inject<HomeViewModel>()
    override val initialState = HomeUiState.Idle

    @Composable
    override fun ViewContent(uiState: HomeUiState, onInputEvent: (HomeInputEvent) -> Unit) {
        Text("Home")
    }
}
