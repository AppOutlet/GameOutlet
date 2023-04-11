package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.game.GameDealStoreUiModel
import appoutlet.gameoutlet.feature.game.GameDealUiModel
import appoutlet.gameoutlet.feature.game.GameUiModel
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun GameDetailsDealsList(uiState: GameUiModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth(),
            text = i18n.tr("Deals"),
            style = MaterialTheme.typography.titleLarge,
        )

        Card(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth(),
        ) {
            Column {
                for ((index, deal) in uiState.deals.withIndex()) {
                    Deal(item = deal)
                    if (index != uiState.deals.lastIndex) {
                        Divider(modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Composable
private fun Deal(item: GameDealUiModel, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        KamelImage(
            modifier = Modifier.padding(MaterialTheme.spacing.medium).size(64.dp),
            resource = lazyPainterResource(item.store.icon),
            contentDescription = null,
            crossfade = true,
        )

        Text(modifier = Modifier.weight(1f), text = item.store.name, style = MaterialTheme.typography.titleLarge)

        Column(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = item.salePrice,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                modifier = Modifier.alpha(.5f),
                text = item.normalPrice,
                style = MaterialTheme.typography.bodySmall,
                textDecoration = TextDecoration.LineThrough
            )
        }
    }
}

@Composable
@Preview
private fun GameDetailsDealsListPreview() {
    val gameUiModel = GameUiModel(
        title = "My amazing game", image = "image.png", deals = listOf(
            GameDealUiModel(
                store = GameDealStoreUiModel(
                    name = "Steam", icon = "SteamIcon.png"
                ), salePrice = "$15.00", normalPrice = "$150.00"
            )
        )
    )

    GameOutletTheme {
        GameDetailsDealsList(gameUiModel)
    }
}
