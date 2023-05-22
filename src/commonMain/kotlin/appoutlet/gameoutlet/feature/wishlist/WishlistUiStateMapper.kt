package appoutlet.gameoutlet.feature.wishlist

import appoutlet.gameoutlet.domain.Game

class WishlistUiStateMapper {
    operator fun invoke(games: List<Game>) = games.map(this::mapWishlistGame)

    private fun mapWishlistGame(game: Game) = WishlistGameUiModel(
        id = game.id,
        title = game.title,
        image = game.image,
    )
}