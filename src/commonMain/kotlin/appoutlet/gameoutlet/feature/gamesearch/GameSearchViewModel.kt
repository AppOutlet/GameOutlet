package appoutlet.gameoutlet.feature.gamesearch

import appoutlet.gameoutlet.feature.common.ViewModel
import appoutlet.gameoutlet.repository.deals.DealRepository
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val SEARCH_DEBOUNCE_TIME = 700.milliseconds

@OptIn(FlowPreview::class)
class GameSearchViewModel(
    private val dealsRepository: DealRepository,
    private val gameSearchUiModelMapper: GameSearchUiModelMapper,
) : ViewModel<GameSearchUiState, GameSearchInputEvent>(initialState = GameSearchUiState.Idle("")) {

    private val mutableSearchTerm = MutableStateFlow("")
    private val mutableUiModels = MutableStateFlow<List<GameSearchUiModel>>(emptyList())

    override fun afterViewModelInitialization() {
        super.afterViewModelInitialization()
        viewModelScope.launch {
            mutableSearchTerm
                .map {
                    mutableUiState.value = GameSearchUiState.Idle(it)
                    it
                }
                .debounce(SEARCH_DEBOUNCE_TIME)
                .distinctUntilChanged()
                .collect {
                    performSearch(it)
                }
        }
    }

    override fun onInputEvent(inputEvent: GameSearchInputEvent) {
        when (inputEvent) {
            is GameSearchInputEvent.GameClicked -> navigateToGameDetails(inputEvent.game)
            is GameSearchInputEvent.Search -> {
                mutableSearchTerm.value = inputEvent.title
            }
        }
    }

    private fun navigateToGameDetails(game: GameSearchUiModel) {

    }

    private fun performSearch(searchTerm: String) {
        viewModelScope.launch {
            mutableUiState.value = GameSearchUiState.Loading(mutableSearchTerm.value, mutableUiModels.value)

            val games = dealsRepository.findGamesByTitle(searchTerm)
            mutableUiModels.value = games.map(gameSearchUiModelMapper::invoke)

            mutableUiState.value = GameSearchUiState.Loaded(mutableSearchTerm.value, mutableUiModels.value)
        }
    }
}
