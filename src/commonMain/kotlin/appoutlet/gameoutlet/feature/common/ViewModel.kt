package appoutlet.gameoutlet.feature.common

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class ViewModel<State : UiState, Event : InputEvent> {
    protected lateinit var viewModelScope: CoroutineScope
    protected lateinit var mutableUiState: MutableStateFlow<State>
    protected lateinit var navigator: Navigator
    val uiState by lazy { mutableUiState.asStateFlow() }

    fun init(scope: CoroutineScope, initialState: State, navigator: Navigator) {
        viewModelScope = scope
        mutableUiState = MutableStateFlow(initialState)
        this.navigator = navigator
    }

    abstract fun onInputEvent(inputEvent: Event)
}
