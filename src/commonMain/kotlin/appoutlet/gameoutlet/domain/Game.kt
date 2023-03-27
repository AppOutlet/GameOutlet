package appoutlet.gameoutlet.domain

data class Game(
    val id: Long,
    val title: String,
    val metacritic: Metacritic,
    val image: String,
)
