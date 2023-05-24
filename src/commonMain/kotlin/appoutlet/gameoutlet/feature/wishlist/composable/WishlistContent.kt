package appoutlet.gameoutlet.feature.wishlist.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.common.composable.Error
import appoutlet.gameoutlet.feature.common.composable.Loading
import appoutlet.gameoutlet.feature.common.composable.ScreenTitle
import appoutlet.gameoutlet.feature.wishlist.WishlistInputEvent
import appoutlet.gameoutlet.feature.wishlist.WishlistUiState

@Composable
fun WishlistContent(
    uiState: WishlistUiState,
    onInputEvent: (WishlistInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        WishlistUiState.Idle -> onInputEvent(WishlistInputEvent.Load)

        WishlistUiState.Error -> Error(
            modifier = Modifier.fillMaxSize().semantics { testTag = "errorIndicator" },
            message = i18n.tr("We were unable to get the saved games"),
            onTryAgain = { onInputEvent(WishlistInputEvent.Load) },
        )

        WishlistUiState.Loading -> Loading(text = i18n.tr("Fetching the saved games for you"))

        is WishlistUiState.Loaded -> WishlistGames(
            uiState = uiState,
            onInputEvent = onInputEvent,
            modifier = modifier
        )
    }
}


@Composable
private fun WishlistGames(
    uiState: WishlistUiState.Loaded,
    onInputEvent: (WishlistInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { ScreenTitle(modifier = Modifier.fillMaxWidth(), text = i18n.tr("Wishlist")) }
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium)) }

        if (uiState.list.isNotEmpty()) {
            items(items = uiState.list, key = { it.id }) { item ->
                WishlistGame(
                    modifier = Modifier.widthIn(max = 600.dp),
                    game = item,
                    onInputEvent = onInputEvent,
                )
            }
        }
    }

    if (uiState.list.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            WishlistEmptyList(onInputEvent = onInputEvent)
        }
    }
}