package appoutlet.gameoutlet.feature.latestdeals.composable

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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
import appoutlet.gameoutlet.feature.latestdeals.LatestDealsInputEvent
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

private val imageHeight = 192.dp
private const val NORMAL_PRICE_ALPHA = .5f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Deal(
    deal: DealUiModel,
    modifier: Modifier = Modifier,
    onInputEvent: (LatestDealsInputEvent) -> Unit
) {
    Card(modifier = modifier.fillMaxWidth().padding(MaterialTheme.spacing.small)) {
        Column(
            modifier = Modifier.clickable { onInputEvent(LatestDealsInputEvent.DealClicked(deal = deal)) },
        ) {
            KamelImage(
                modifier = Modifier.fillMaxWidth()
                    .height(imageHeight)
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer),
                resource = lazyPainterResource(data = deal.gameImage),
                contentDescription = null,
                animationSpec = tween(),
                onLoading = {
                    Box(
                        modifier = Modifier.height(imageHeight)
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    )
                },
                contentScale = ContentScale.Crop,
            )

            Row(modifier = Modifier) {
                Column(
                    modifier = modifier.weight(1f).padding(MaterialTheme.spacing.medium)
                ) {
                    Text(
                        modifier = Modifier.height(48.dp),
                        text = deal.gameTitle,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Medium,
                    )

                    StoresRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = MaterialTheme.spacing.medium,
                                bottom = MaterialTheme.spacing.small
                            ),
                        uiModel = deal,
                    )
                }


                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                    ) {
                        Box(
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.medium
                            )
                        ) {
                            Text(deal.savings, style = MaterialTheme.typography.titleMedium)
                        }
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                    Text(
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                        text = deal.currentPrice,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        modifier = Modifier.alpha(NORMAL_PRICE_ALPHA)
                            .padding(horizontal = MaterialTheme.spacing.medium),
                        text = deal.oldPrice,
                        style = MaterialTheme.typography.labelSmall,
                        textDecoration = TextDecoration.LineThrough,
                    )
                }
            }
        }
    }
}

@Immutable
data class DealUiModel(
    val gameTitle: String,
    val currentPrice: String,
    val gameId: Long,
    val gameImage: String,
    val oldPrice: String,
    val savings: String,
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
        savings = "% 50",
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
        Deal(dealSample, onInputEvent = { })
    }
}
