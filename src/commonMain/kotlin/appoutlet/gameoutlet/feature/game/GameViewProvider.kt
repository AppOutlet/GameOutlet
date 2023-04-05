package appoutlet.gameoutlet.feature.game

class GameViewProvider {
    fun getGameView(navArgs: GameNavArgs) = GameView(navArgs)
}

data class GameNavArgs(val gameId: Long, val gameTitle: String, val gameImage: String)
