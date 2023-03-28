package appoutlet.gameoutlet.feature.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.feature.common.View
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import org.koin.core.component.inject

class GameView(private val gameId: Long) : View<GameUiState, GameInputEvent>() {
    override val viewModel by inject<GameViewModel>()

    @Composable
    override fun ViewContent(uiState: GameUiState, onInputEvent: (GameInputEvent) -> Unit) {
        Column(modifier = Modifier.fillMaxSize()) {
            ScreenTitle(gameId.toString())
            Button(onClick = { onInputEvent(GameInputEvent.NavigateBack) }) {
                Text("Back")
            }
        }
    }
}