package appoutlet.gameoutlet.feature.latestdeals.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.ui.spacing
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoresRow(uiModel: DealUiModel, modifier: Modifier = Modifier) {
    val iconSize = 18.dp
    Row(modifier = modifier, verticalAlignment = Alignment.Bottom) {
        uiModel.stores.forEach { store ->
            TooltipArea(
                modifier = Modifier.semantics { testTag = "${store.name} icon" },
                tooltip = {
                    Text(
                        modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = MaterialTheme.shapes.small
                        ).padding(MaterialTheme.spacing.extraSmall)
                            .semantics { testTag = "${store.name} tooltip" },
                        text = store.name,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                content = {
                    KamelImage(
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall)
                            .size(iconSize),
                        resource = lazyPainterResource(data = store.icon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        onLoading = {
                            Box(
                                modifier = Modifier.size(iconSize)
                                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                            )
                        },
                    )
                },
            )
        }
    }
}
