package appoutlet.gameoutlet.feature.latestdeals.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.ui.spacing
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoresRow(stores: List<DealStoreUiModel>, modifier: Modifier = Modifier) {
    val iconSize = 18.dp
    Row(modifier = modifier, verticalAlignment = Alignment.Bottom) {
        stores.forEach { store ->
            TooltipArea(
                tooltip = {
                    Text(
                        modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = MaterialTheme.shapes.small
                        ).padding(MaterialTheme.spacing.verySmall),
                        text = store.name,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                content = {
                    KamelImage(
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.verySmall).size(iconSize),
                        resource = lazyPainterResource(data = store.icon),
                        contentDescription = null,
                        crossfade = false,
                        contentScale = ContentScale.Crop,
                        onLoading = {
                            Box(
                                modifier = Modifier.size(iconSize)
                                    .padding(horizontal = MaterialTheme.spacing.verySmall)
                                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                            )
                        },
                    )
                },
            )
        }
    }
}