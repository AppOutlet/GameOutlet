package appoutlet.gameoutlet.feature.game

import appoutlet.gameoutlet.core.util.DesktopHelper
import appoutlet.gameoutlet.domain.Deal
import appoutlet.gameoutlet.domain.Game
import appoutlet.gameoutlet.feature.common.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class GameViewModel(
    private val gameOrchestrator: GameOrchestrator,
    private val gameUiModelMapper: GameUiModelMapper,
    private val desktopHelper: DesktopHelper,
) : ViewModel<GameUiState, GameInputEvent>(initialState = GameUiState.Idle) {
    private lateinit var gameJob: Job

    private val _game = MutableStateFlow(value = Game.UNSET)
    private val _deals = MutableStateFlow<List<Deal>>(value = emptyList())
    private val _isGameSaved = MutableStateFlow(value = false)
    private var shouldShowSnackBar: Boolean = false

    override fun afterViewModelInitialization() {
        gameJob = combine(_deals, _game, _isGameSaved) { deals, game, isGameSaved ->
            when {
                game == Game.UNSET -> null
                deals.isEmpty() -> null
                else -> {
                    gameUiModelMapper.invoke(game, deals, isGameSaved, shouldShowSnackBar)
                }
            }
        }
            .filterNotNull()
            .onEach { gameUiModel ->
                mutableUiState.value = GameUiState.Loaded(gameUiModel = gameUiModel)
            }
            .launchIn(viewModelScope)
    }

    override fun onInputEvent(inputEvent: GameInputEvent) {
        when (inputEvent) {
            GameInputEvent.NavigateBack -> navigator.pop()

            is GameInputEvent.Load -> loadGame(inputEvent.gameNavArgs)

            is GameInputEvent.DealClicked -> {
                openDealLink(inputEvent.deal)
            }

            is GameInputEvent.SaveGame -> saveGame(inputEvent.game)

            is GameInputEvent.RemoveGameFromFavorites -> removeGame(inputEvent.game)
        }
    }

    private fun loadGame(gameNavArgs: GameNavArgs) {
        val game = Game(
            id = gameNavArgs.gameId,
            title = gameNavArgs.gameTitle,
            image = gameNavArgs.gameImage,
        )

        _game.value = game

        checkIfGameIsSaved(game)
        findDealsByGame(game)
    }

    private fun findDealsByGame(game: Game) {
        gameOrchestrator.findDealsByGame(game)
            .onStart { mutableUiState.value = GameUiState.Loading }
            .catch { mutableUiState.value = GameUiState.Error }
            .onEach { deals ->
                _deals.value = deals
            }
            .launchIn(viewModelScope)
    }

    private fun checkIfGameIsSaved(game: Game) {
        _isGameSaved.value = gameOrchestrator.checkIfGameIsSaved(game)
    }

    private fun saveGame(game: Game) {
        gameOrchestrator.save(game)
        shouldShowSnackBar = true
        checkIfGameIsSaved(game)
    }

    private fun openDealLink(deal: GameDealUiModel) {
        desktopHelper.openLink("https://www.cheapshark.com/redirect?dealID=${deal.id}")
    }

    private fun removeGame(game: Game) {
        gameOrchestrator.removeGame(game)
        shouldShowSnackBar = true
        checkIfGameIsSaved(game)
    }
}
