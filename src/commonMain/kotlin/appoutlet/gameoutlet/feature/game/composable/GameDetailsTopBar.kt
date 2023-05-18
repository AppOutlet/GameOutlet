package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import appoutlet.gameoutlet.feature.game.GameInputEvent
import appoutlet.gameoutlet.feature.game.GameUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsTopBar(
    uiState: GameUiModel,
    onInputEvent: (GameInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = uiState.title)
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.semantics { testTag = "navigation icon" },
                onClick = { onInputEvent(GameInputEvent.NavigateBack) },
                content = {
                    Icon(Icons.Outlined.ArrowBack, null)
                }
            )
        },
        actions = {
            val (icon, color) = if (uiState.favouriteButton.isSaved) {
                Icons.Outlined.Favorite to MaterialTheme.colorScheme.error
            } else {
                Icons.Outlined.FavoriteBorder to LocalContentColor.current
            }

            IconButton(
                modifier = Modifier.semantics {
                    contentDescription = if (uiState.favouriteButton.isSaved) {
                        "remove game icon"
                    } else {
                        "save game icon"
                    }
                },
                onClick = { onInputEvent(uiState.favouriteButton.inputEvent) },
                content = { Icon(icon, null, tint = color) }
            )
        }
    )
}
