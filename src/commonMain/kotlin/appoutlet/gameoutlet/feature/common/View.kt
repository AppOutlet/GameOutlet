package appoutlet.gameoutlet.feature.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.core.component.KoinComponent

abstract class View<State : UiState, Event : InputEvent> : Screen, KoinComponent {
    abstract val viewModel: ViewModel<State, Event>

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val coroutineScope = rememberCoroutineScope()
        requireNotNull(navigator) { "Navigator is not available" }
        viewModel.init(coroutineScope, navigator)
        InternalContent()
    }

    @Composable
    private fun InternalContent() {
        val uiState by viewModel.uiState.collectAsState()
        ViewContent(uiState = uiState, onInputEvent = viewModel::onInputEvent)
    }

    @Composable
    abstract fun ViewContent(uiState: State, onInputEvent: (Event) -> Unit)
}
