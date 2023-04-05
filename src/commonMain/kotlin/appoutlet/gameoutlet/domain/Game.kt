package appoutlet.gameoutlet.domain

data class Game(
    val id: Long,
    val title: String,
    val image: String,
    val metacritic: Metacritic? = null,
    val steam: Steam? = null,
)
