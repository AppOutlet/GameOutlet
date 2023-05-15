package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.game.GameUiModel
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun GameDetailsImage(uiState: GameUiModel, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        KamelImage(
            modifier = Modifier
                .matchParentSize()
                .blur(10.dp),
            resource = lazyPainterResource(data = uiState.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.background.copy(alpha = .5f),
                blendMode = BlendMode.Lighten
            )
        )

        KamelImage(
            modifier = Modifier.padding(MaterialTheme.spacing.medium),
            resource = lazyPainterResource(data = uiState.image),
            contentDescription = null,
            animationSpec = tween(),
        )
    }
}