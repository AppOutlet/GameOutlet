package appoutlet.gameoutlet.feature.storelist.composable

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.storelist.StoreListInputEvent
import appoutlet.gameoutlet.feature.storelist.StoreListUiState
import appoutlet.gameoutlet.feature.storelist.StoreUiModel
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun StoreList(
    uiState: StoreListUiState.Loaded,
    onInputEvent: (StoreListInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(uiState.stores) { store ->
            Store(
                uiModel = store,
                onInputEvent = onInputEvent
            )
        }
    }
}

@Composable
private fun Store(
    uiModel: StoreUiModel,
    onInputEvent: (StoreListInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.widthIn(max = 512.dp).padding(all = MaterialTheme.spacing.small)) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .clickable { onInputEvent(uiModel.inputEvent) }
                .padding(all = MaterialTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            uiModel.icon?.let {
                KamelImage(
                    modifier = Modifier.size(64.dp),
                    resource = lazyPainterResource(data = uiModel.icon),
                    contentDescription = null,
                    animationSpec = tween(),
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            }

            Text(uiModel.name)
        }
    }
}
