package appoutlet.gameoutlet.feature.gamesearch.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.common.composable.Error
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import appoutlet.gameoutlet.feature.gamesearch.GameSearchInputEvent
import appoutlet.gameoutlet.feature.gamesearch.GameSearchUiModel
import appoutlet.gameoutlet.feature.gamesearch.GameSearchUiState
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun GameSearchContent(
    uiState: GameSearchUiState,
    onInputEvent: (GameSearchInputEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        ScreenTitle(modifier = Modifier.fillMaxWidth(), text = i18n.tr("Search"))
        GameSearchTextField(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth(),
            onValueChange = {
                onInputEvent(GameSearchInputEvent.Search(it))
            },
            isLoading = uiState is GameSearchUiState.Loading
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        LazyColumn(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
        ) {
            items(uiState.games) { game ->
                GameSearchItem(game = game, onInputEvent = onInputEvent)
            }
        }

        AnimatedVisibility(visible = uiState is GameSearchUiState.Error) {
            Error(modifier.fillMaxSize()) {
                onInputEvent(GameSearchInputEvent.Search(uiState.searchTerm))
            }
        }

        AnimatedVisibility(visible = uiState is GameSearchUiState.Idle) {
            Text(i18n.tr("Start by searching some game in the field above"))
        }
    }
}

@Composable
private fun GameSearchItem(
    game: GameSearchUiModel,
    onInputEvent: (GameSearchInputEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(MaterialTheme.spacing.small),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(150.dp)
                .clickable {
                    onInputEvent(GameSearchInputEvent.GameClicked(game))
                },
        ) {
            KamelImage(
                modifier = Modifier.fillMaxHeight().weight(.4f),
                resource = lazyPainterResource(data = game.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                onLoading = {
                    Box(
                        modifier = Modifier.fillMaxHeight()
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                            .weight(.4f)
                    ) {}
                }
            )

            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.medium).weight(.6f),
                text = game.title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
@Preview
private fun GameSearchContentPreview() {
    GameOutletTheme {
        val games = listOf(
            GameSearchUiModel(id = 123L, title = "My game", image = "")
        )
        GameSearchContent(GameSearchUiState.Loaded("Some game", games), {})
    }
}

@Composable
@Preview
private fun GameSearchItemPreview() {
    GameOutletTheme {
        GameSearchItem(
            game = GameSearchUiModel(id = 123L, title = "My game", image = ""),
            onInputEvent = {}
        )
    }
}
