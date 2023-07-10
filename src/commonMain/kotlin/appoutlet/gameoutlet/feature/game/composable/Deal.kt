package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.game.GameDealStoreUiModel
import appoutlet.gameoutlet.feature.game.GameDealUiModel
import appoutlet.gameoutlet.feature.game.GameInputEvent
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun Deal(
    item: GameDealUiModel,
    onInputEvent: (GameInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(vertical = MaterialTheme.spacing.small),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .clickable {
                    onInputEvent(GameInputEvent.DealClicked(item))
                }.semantics {
                    testTag = item.id
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            KamelImage(
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.large
                )
                    .size(56.dp)
                    .clip(CircleShape),
                resource = lazyPainterResource(item.store.icon),
                contentDescription = null,
                animationSpec = tween(),
                onLoading = {
                    Box(
                        modifier = Modifier.padding(MaterialTheme.spacing.medium).size(64.dp),
                    ) { }
                }
            )

            Text(
                modifier = Modifier.weight(1f),
                text = item.store.name,
                style = MaterialTheme.typography.titleLarge
            )

            Column(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                horizontalAlignment = Alignment.End
            ) {
                if (item.showNormalPrice) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                    ) {
                        Box(
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.medium
                            )
                        ) {
                            Text(item.savings, style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Text(
                    modifier = Modifier.padding(end = MaterialTheme.spacing.medium),
                    text = item.salePrice,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                if (item.showNormalPrice) {
                    Text(
                        modifier = Modifier.padding(end = MaterialTheme.spacing.medium),
                        text = item.normalPrice,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
        }
    }
}

@Composable
@Preview
private fun DealPreview() {
    val deal = GameDealUiModel(
        id = "123",
        store = GameDealStoreUiModel(
            name = "Store",
            icon = ""
        ),
        salePrice = "123",
        normalPrice = "123",
        savings = "- 20%",
        showNormalPrice = true
    )

    GameOutletTheme {
        Deal(deal, {})
    }
}
