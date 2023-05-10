package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.game.GameDealStoreUiModel
import appoutlet.gameoutlet.feature.game.GameDealUiModel
import appoutlet.gameoutlet.feature.game.GameInputEvent
import appoutlet.gameoutlet.feature.game.GameUiModel
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun GameDetailsDealsList(uiState: GameUiModel, onInputEvent: (GameInputEvent) -> Unit, modifier: Modifier = Modifier) {
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
                    Deal(item = deal, onInputEvent = onInputEvent)
                    if (index != uiState.deals.lastIndex) {
                        Divider(Modifier.fillMaxWidth())
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .padding(vertical = MaterialTheme.spacing.large, horizontal = MaterialTheme.spacing.medium)
                .fillMaxWidth(),
        ) {
            Icon(imageVector = Icons.Outlined.Warning, contentDescription = null)

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Text(
                text = i18n.tr(
                    "The prices are in US dollar (USD). Note that the value can change depending on your location"
                ),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun Deal(item: GameDealUiModel, onInputEvent: (GameInputEvent) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth()
            .clickable {
                onInputEvent(GameInputEvent.DealClicked(item))
            }.semantics {
                testTag = "deal ${item.id}"
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        KamelImage(
            modifier = Modifier.padding(MaterialTheme.spacing.medium).size(64.dp),
            resource = lazyPainterResource(item.store.icon),
            contentDescription = null,
            animationSpec = tween(),
            onLoading = {
                Box(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium).size(64.dp),
                ) { }
            }
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

            if (item.showNormalPrice) {
                Text(
                    text = item.normalPrice,
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = TextDecoration.LineThrough
                )
            }
        }
    }
}

@Composable
@Preview
private fun GameDetailsDealsListPreview() {
    val gameUiModel = GameUiModel(
        title = "Game",
        image = "",
        deals = listOf(
            GameDealUiModel(
                id = "123",
                store = GameDealStoreUiModel(
                    name = "Store",
                    icon = ""
                ),
                salePrice = "123",
                normalPrice = "123",
                showNormalPrice = true
            )
        )
    )
    GameOutletTheme {
        GameDetailsDealsList(gameUiModel, {})
    }
}
