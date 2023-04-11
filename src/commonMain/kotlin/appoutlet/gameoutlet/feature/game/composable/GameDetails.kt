package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import appoutlet.gameoutlet.feature.game.GameInputEvent
import appoutlet.gameoutlet.feature.game.GameUiModel
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun GameDetails(uiState: GameUiModel, modifier: Modifier = Modifier, onInputEvent: (GameInputEvent) -> Unit) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Box(modifier = Modifier.fillMaxWidth()) {
            KamelImage(
                modifier = Modifier
                    .matchParentSize()
                    .blur(10.dp),
                resource = lazyPainterResource(data = uiState.image),
                contentDescription = null,
                crossfade = false,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.background.copy(alpha = .5f),
                    blendMode = BlendMode.Lighten
                )
            )

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                KamelImage(
                    modifier = Modifier.padding(MaterialTheme.spacing.large)
                        .clip(MaterialTheme.shapes.large),
                    resource = lazyPainterResource(data = uiState.image),
                    contentDescription = null,
                    crossfade = true
                )
            }
        }
        Row(modifier = Modifier.padding(MaterialTheme.spacing.medium).fillMaxWidth()) {
            IconButton(
                modifier = Modifier.padding(top = MaterialTheme.spacing.large),
                onClick = { onInputEvent(GameInputEvent.NavigateBack) },
                content = { Icon(Icons.Outlined.ArrowBack, null) }
            )

            ScreenTitle(text = uiState.title)
        }

        GameDetailsDealsList(uiState = uiState, onInputEvent = onInputEvent)
    }
}
