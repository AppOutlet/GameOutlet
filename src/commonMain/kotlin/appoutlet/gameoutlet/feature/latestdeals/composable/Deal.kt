package appoutlet.gameoutlet.feature.latestdeals.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Deal(deal: DealUiModel, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth().padding(MaterialTheme.spacing.verySmall)) {
        Column {
            KamelImage(
                modifier = Modifier.fillMaxWidth().height(200.dp)
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer),
                resource = lazyPainterResource(data = deal.gameImage),
                contentDescription = null,
                crossfade = true,
                onLoading = {
                    Box(modifier = Modifier.height(200.dp).fillMaxWidth())
                },
                contentScale = ContentScale.Crop,
            )

            Row(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                Text(
                    modifier = Modifier.weight(1f).heightIn(min = 60.dp),
                    text = deal.gameTitle,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Column(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = deal.currentPrice,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        modifier = Modifier.alpha(.5f),
                        text = deal.oldPrice,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.LineThrough,
                    )
                }
            }

            Row(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                    .padding(bottom = MaterialTheme.spacing.medium),
            ) {
                deal.stores.forEach { store ->
                    TooltipArea(tooltip = {
                        Text(
                            modifier = Modifier.background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = MaterialTheme.shapes.small
                            ).padding(MaterialTheme.spacing.verySmall),
                            text = store.name,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }, content = {
                        KamelImage(
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small).size(24.dp),
                            resource = lazyPainterResource(data = store.icon),
                            contentDescription = null,
                            crossfade = false,
                            contentScale = ContentScale.Crop,
                            onLoading = {
                                Box(modifier = Modifier.height(24.dp).fillMaxWidth())
                            },
                        )
                    })
                }
            }
        }
    }
}

data class DealUiModel(
    val gameTitle: String,
    val currentPrice: String,
    val gameId: String,
    val gameImage: String,
    val oldPrice: String,
    val stores: List<DealStoreUiModel>
)

data class DealStoreUiModel(
    val icon: String,
    val name: String
)

@Composable
@Preview
private fun DealPreview() {
    GameOutletTheme {
        val deal = DealUiModel(
            gameId = "qwe",
            gameImage =  "https://i.ytimg.com/vi/4PVYl2YigQg/maxresdefault.jpg",
            gameTitle = "The end of the fucking world",
            currentPrice = "49",
            oldPrice = "100",
            stores = listOf(
                DealStoreUiModel(
                    icon = "https://www.cheapshark.com/img/stores/logos/3.png",
                    name = "Amazon"
                ),
                DealStoreUiModel(
                    icon = "https://www.cheapshark.com/img/stores/logos/3.png",
                    name = "Steam"
                )
            )
        )
        Deal(deal)
    }
}