package appoutlet.gameoutlet.feature.store.composable

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.store.DealViewData
import appoutlet.gameoutlet.feature.store.StoreInputEvent
import appoutlet.gameoutlet.feature.store.StoreViewData
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

private val imageHeight = 200.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoreDealList(
    viewData: StoreViewData,
    onInputEvent: (StoreInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(minSize = 256.dp)
    ) {
        items(viewData.deals) { deal ->
            Deal(
                modifier = Modifier.fillMaxWidth(),
                viewData = deal,
                onInputEvent = onInputEvent,
            )
        }
    }
}

@Suppress("MagicNumber")
@Composable
private fun Deal(
    viewData: DealViewData,
    onInputEvent: (StoreInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth().padding(MaterialTheme.spacing.small)) {
        Column(
            modifier = Modifier.clickable { onInputEvent(viewData.inputEvent) },
        ) {
            KamelImage(
                modifier = Modifier.fillMaxWidth()
                    .height(imageHeight)
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer),
                resource = lazyPainterResource(data = viewData.image),
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

            Row(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = viewData.title,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                )

                Column(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = viewData.currentPrice,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    viewData.normalPrice?.let {
                        Text(
                            modifier = Modifier.alpha(.5f),
                            text = it,
                            style = MaterialTheme.typography.labelSmall,
                            textDecoration = TextDecoration.LineThrough,
                        )
                    }
                }
            }
        }
    }
}
