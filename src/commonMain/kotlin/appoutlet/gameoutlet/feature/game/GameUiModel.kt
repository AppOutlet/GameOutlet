package appoutlet.gameoutlet.feature.game

import androidx.compose.runtime.Immutable

@Immutable
data class GameUiModel(
    val title: String,
    val image: String,
    val deals: List<GameDealUiModel>
)

data class GameDealUiModel(
    val id: String,
    val store: GameDealStoreUiModel,
    val salePrice: String,
    val normalPrice: String,
)

data class GameDealStoreUiModel(
    val name: String,
    val icon: String,
)
