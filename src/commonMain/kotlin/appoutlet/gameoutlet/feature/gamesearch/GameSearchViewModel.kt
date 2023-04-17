package appoutlet.gameoutlet.feature.gamesearch

import appoutlet.gameoutlet.feature.common.ViewModel
import appoutlet.gameoutlet.feature.game.GameNavArgs
import appoutlet.gameoutlet.feature.game.GameViewProvider
import appoutlet.gameoutlet.repository.deals.DealRepository
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

private val SEARCH_DEBOUNCE_TIME = 700.milliseconds

@OptIn(FlowPreview::class)
class GameSearchViewModel(
    private val dealsRepository: DealRepository,
    private val gameSearchUiModelMapper: GameSearchUiModelMapper,
    private val gameViewProvider: GameViewProvider,
) : ViewModel<GameSearchUiState, GameSearchInputEvent>(initialState = GameSearchUiState.Idle("")) {

    private val mutableSearchTerm = MutableStateFlow("")
    private val mutableUiModels = MutableStateFlow<List<GameSearchUiModel>>(emptyList())

    override fun afterViewModelInitialization() {
        super.afterViewModelInitialization()
        viewModelScope.launch {
            mutableSearchTerm
                .filter { it.isNotBlank() }
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
        val navArgs = GameNavArgs(
            gameId = game.id,
            gameTitle = game.title,
            gameImage = game.image
        )

        navigator.push(gameViewProvider.getGameView(navArgs))
    }

    private fun performSearch(searchTerm: String) {
        viewModelScope.launch {
            try {
                mutableUiState.value = GameSearchUiState.Loading(mutableSearchTerm.value, mutableUiModels.value)
                val games = dealsRepository.findGamesByTitle(searchTerm)
                mutableUiModels.value = games.map(gameSearchUiModelMapper::invoke)
                mutableUiState.value = GameSearchUiState.Loaded(mutableSearchTerm.value, mutableUiModels.value)
            } catch (exception: Exception) {
                mutableUiState.value = GameSearchUiState.Error(mutableSearchTerm.value, mutableUiModels.value)
            }
        }
    }
}
