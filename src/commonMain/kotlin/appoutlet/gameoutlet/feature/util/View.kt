package appoutlet.gameoutlet.feature.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.component.KoinComponent

abstract class View<State : UiState, Event : InputEvent> : Screen, KoinComponent {
    abstract val viewModel: ViewModel<State, Event>
    abstract val initialState: State

    @Composable
    override fun Content() {
        viewModel.init(rememberCoroutineScope(), initialState)
        val uiState by viewModel.uiState.collectAsState()
        ViewContent(uiState = uiState, onInputEvent = viewModel::onInputEvent)
    }

    @Composable
    abstract fun ViewContent(uiState: State, onInputEvent: (Event) -> Unit)
}
