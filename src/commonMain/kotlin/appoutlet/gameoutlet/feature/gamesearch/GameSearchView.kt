package appoutlet.gameoutlet.feature.gamesearch

import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.gamesearch.composable.GameSearchContent
import org.koin.core.component.inject

class GameSearchView : View<GameSearchUiState, GameSearchInputEvent>() {
    override val viewModel by inject<GameSearchViewModel>()

    @Composable
    override fun ViewContent(uiState: GameSearchUiState, onInputEvent: (GameSearchInputEvent) -> Unit) {
        GameSearchContent(uiState = uiState, onInputEvent = onInputEvent)
    }
}
