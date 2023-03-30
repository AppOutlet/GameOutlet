package appoutlet.gameoutlet.feature.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.common.composable.Error
import appoutlet.gameoutlet.feature.common.composable.Loading
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import appoutlet.gameoutlet.feature.game.composable.GameDetails
import org.koin.core.component.inject

class GameView(private val gameId: Long) : View<GameUiState, GameInputEvent>() {
    override val viewModel by inject<GameViewModel>()

    @Composable
    override fun ViewContent(uiState: GameUiState, onInputEvent: (GameInputEvent) -> Unit) {
        GameViewContent(gameId, uiState, onInputEvent)
    }
}


@Composable
private fun GameViewContent(gameId: Long, uiState: GameUiState, onInputEvent: (GameInputEvent) -> Unit) {
    when (uiState) {
        GameUiState.Idle -> onInputEvent(GameInputEvent.Load(gameId))

        GameUiState.Error -> Error(
            modifier = Modifier.fillMaxSize(),
            message = i18n.tr("We could not load the data from the selected game"),
            onTryAgain = { onInputEvent(GameInputEvent.Load(gameId)) }
        )

        GameUiState.Loading -> Loading(
            modifier = Modifier.fillMaxSize(),
            text = i18n.tr("We are fetching the deals from the selected game")
        )

        is GameUiState.Loaded -> GameDetails(uiState.gameUiModel, onInputEvent)
    }
}