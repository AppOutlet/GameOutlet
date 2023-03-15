package appoutlet.gameoutlet.feature.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class ViewModel<State : UiState, Event : InputEvent> {
    protected lateinit var viewModelScope: CoroutineScope
    protected lateinit var mutableUiState: MutableStateFlow<State>
    val uiState by lazy { mutableUiState.asStateFlow() }

    fun init(scope: CoroutineScope, initialState: State) {
        viewModelScope = scope
        mutableUiState = MutableStateFlow(initialState)
    }

    abstract fun onInputEvent(inputEvent: Event)
}
