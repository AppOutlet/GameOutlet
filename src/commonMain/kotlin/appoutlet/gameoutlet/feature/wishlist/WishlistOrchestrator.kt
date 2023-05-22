package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.repository.game.GameRepository
import kotlinx.coroutines.flow.flow

class WishlistOrchestrator(
    private val gameRepository: GameRepository,
) {
    fun findAll() = flow {
        emit(gameRepository.findAll())
    }
}