package appoutlet.gameoutlet.feature.game

import androidx.compose.runtime.Immutable

@Immutable
data class GameUiModel(
    val title: String,
    val image: String,
    val deals: List<GameDealUiModel>,
    val favouriteButton: GameFavouriteButton,
)

data class GameDealUiModel(
    val id: String,
    val store: GameDealStoreUiModel,
    val salePrice: String,
    val normalPrice: String,
    val showNormalPrice: Boolean,
)

data class GameDealStoreUiModel(
    val name: String,
    val icon: String,
)

data class GameFavouriteButton(
    val isSaved: Boolean,
    val inputEvent: GameInputEvent
)
