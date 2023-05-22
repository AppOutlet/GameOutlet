package appoutlet.gameoutlet.feature.wishlist.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.wishlist.WishlistGameUiModel
import appoutlet.gameoutlet.feature.wishlist.WishlistInputEvent
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

private const val IMAGE_WEIGHT = .4f
private const val TEXT_WEIGHT = .6f

@Composable
fun WishlistGame(
    game: WishlistGameUiModel,
    onInputEvent: (WishlistInputEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(MaterialTheme.spacing.small),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(150.dp)
                .clickable {
                    onInputEvent(WishlistInputEvent.GameClicked(game))
                },
        ) {
            KamelImage(
                modifier = Modifier.fillMaxHeight().weight(IMAGE_WEIGHT),
                resource = lazyPainterResource(data = game.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                onLoading = {
                    Box(
                        modifier = Modifier.fillMaxHeight()
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                    ) {}
                }
            )

            Column(modifier = Modifier.weight(TEXT_WEIGHT)) {
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f)
                        .padding(MaterialTheme.spacing.medium),
                    text = game.title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
