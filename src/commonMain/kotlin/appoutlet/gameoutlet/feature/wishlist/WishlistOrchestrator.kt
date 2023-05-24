package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.repository.game.GameRepository

class WishlistOrchestrator(
    private val gameRepository: GameRepository,
) {
    fun findAll() = gameRepository.findAll()
}
