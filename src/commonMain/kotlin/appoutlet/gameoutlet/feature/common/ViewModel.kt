package appoutlet.gameoutlet.feature.common

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class ViewModel<State : UiState, Event : InputEvent>(initialState: State) {
    protected lateinit var viewModelScope: CoroutineScope
    protected val mutableUiState = MutableStateFlow(initialState)
    protected lateinit var navigator: Navigator
    val uiState = mutableUiState.asStateFlow()

    fun init(scope: CoroutineScope, navigator: Navigator) {
        viewModelScope = scope
        this.navigator = navigator
        println("View model initialized")
        afterViewModelInitialization()
    }

    abstract fun onInputEvent(inputEvent: Event)

    open fun afterViewModelInitialization() {}
}
