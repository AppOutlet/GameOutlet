package appoutlet.gameoutlet.feature.latestdeals.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
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

private val imageHeight = 176.dp
@Composable
fun Deal(deal: DealUiModel, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth().padding(MaterialTheme.spacing.small)) {
        Column {
            KamelImage(
                modifier = Modifier.fillMaxWidth()
                    .height(imageHeight)
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer),
                resource = lazyPainterResource(data = deal.gameImage),
                contentDescription = null,
                crossfade = true,
                onLoading = {
                    Box(
                        modifier = Modifier.height(imageHeight)
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    )
                },
                contentScale = ContentScale.Crop,
            )

            Row(
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            ) {
                Text(
                    modifier = Modifier.heightIn(min = 72.dp).weight(1f),
                    text = deal.gameTitle,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                )

                Column(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = deal.currentPrice,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.alpha(.5f),
                        text = deal.oldPrice,
                        style = MaterialTheme.typography.labelSmall,
                        textDecoration = TextDecoration.LineThrough,
                    )
                }
            }

            StoresRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.small)
                    .padding(bottom = MaterialTheme.spacing.medium),
                stores = deal.stores
            )
        }
    }
}

data class DealUiModel(
    val gameTitle: String,
    val currentPrice: String,
    val gameId: Long,
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
    val dealSample = DealUiModel(
        gameId = 123,
        gameImage = "https://i.ytimg.com/vi/4PVYl2YigQg/maxresdefault.jpg",
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
    GameOutletTheme {
        Deal(dealSample)
    }
}