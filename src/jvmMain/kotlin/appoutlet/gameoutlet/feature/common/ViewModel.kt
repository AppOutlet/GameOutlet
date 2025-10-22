package appoutlet.gameoutlet.feature.common

import cafe.adriel.voyager.navigator.Navigator
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class ViewModel<State : UiState, Event : InputEvent>(initialState: State) {
    protected lateinit var viewModelScope: CoroutineScope
    protected val mutableUiState = MutableStateFlow(initialState)
    protected lateinit var navigator: Navigator
    val uiState = mutableUiState.asStateFlow()
    var viewModelJob: Job? = null

    fun init(scope: CoroutineScope, navigator: Navigator) {
        viewModelScope = scope
        this.navigator = navigator
        Napier.v(message = "ViewModel initialized")
        afterViewModelInitialization()
    }

    abstract fun onInputEvent(inputEvent: Event)

    open fun afterViewModelInitialization() {}
}
