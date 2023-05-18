package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.feature.game.GameDealStoreUiModel
import appoutlet.gameoutlet.feature.game.GameDealUiModel
import appoutlet.gameoutlet.feature.game.GameFavouriteButton
import appoutlet.gameoutlet.feature.game.GameInputEvent
import appoutlet.gameoutlet.feature.game.GameUiModel

@Composable
@Preview
fun GameDetailPreview() {
    GameOutletTheme {
        val uiModel = GameUiModel(
            title = "Some amazing game",
            image = "Some image",
            deals = listOf(
                GameDealUiModel(
                    id = "id",
                    store = GameDealStoreUiModel(
                        name = "Some store",
                        icon = "Some store logo",
                    ),
                    salePrice = "$ 12",
                    normalPrice = "$ 100",
                    showNormalPrice = true
                )
            ),
            favouriteButton = GameFavouriteButton(
                isSaved = false,
                inputEvent = GameInputEvent.NavigateBack
            ),
            snackBarMessage = null
        )
        GameDetails(uiState = uiModel, onInputEvent = {})
    }
}
