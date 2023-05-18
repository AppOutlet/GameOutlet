package appoutlet.gameoutlet.domain

data class Game(
    val id: Long,
    val title: String,
    val image: String,
    val metacritic: Metacritic? = null,
    val steam: Steam? = null,
) {
    companion object {
        val UNSET = Game(
            id = Long.MIN_VALUE,
            title = "",
            image = ""
        )
    }
}
