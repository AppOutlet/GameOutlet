package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import appoutlet.gameoutlet.feature.game.GameDealStoreUiModel
import appoutlet.gameoutlet.feature.game.GameDealUiModel
import appoutlet.gameoutlet.feature.game.GameInputEvent
import appoutlet.gameoutlet.feature.game.GameUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetails(uiState: GameUiModel, onInputEvent: (GameInputEvent) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onInputEvent(GameInputEvent.NavigateBack)
                        },
                        content = {
                            Icon(Icons.Outlined.ArrowBack, null)
                        }
                    )
                },
                title = {
                    ScreenTitle(text = uiState.title)
                },
            )
        },
        content = {},
    )
}

@Composable
@Preview
private fun GameDetailsPreview() {
    val gameUiModel = GameUiModel(
        title = "My amazing game",
        image = "image.png",
        deals = listOf(
            GameDealUiModel(
                store = GameDealStoreUiModel(
                    name = "Steam",
                    icon = "SteamIcon.png"
                ),
                salePrice = "$15.00",
                normalPrice = "$150.00"
            )
        )
    )

    GameOutletTheme {
        GameDetails(gameUiModel) {}
    }
}