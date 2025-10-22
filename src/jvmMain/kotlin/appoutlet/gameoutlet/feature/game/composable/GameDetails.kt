package appoutlet.gameoutlet.feature.game.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.game.GameInputEvent
import appoutlet.gameoutlet.feature.game.GameUiModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetails(
    uiState: GameUiModel,
    modifier: Modifier = Modifier,
    onInputEvent: (GameInputEvent) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    uiState.snackBarMessage?.let {
        coroutineScope.launch {
            snackBarHostState.showSnackbar(message = it, withDismissAction = true)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            GameDetailsTopBar(uiState = uiState, onInputEvent = onInputEvent)
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item(key = uiState.image) {
                GameDetailsImage(uiState = uiState)
            }

            item {
                Text(
                    modifier = Modifier
                        .widthIn(max = 500.dp)
                        .padding(MaterialTheme.spacing.small)
                        .fillMaxWidth(),
                    text = i18n.tr("Deals"),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium
                )
            }

            items(items = uiState.deals) { deal ->
                Deal(
                    modifier = Modifier.widthIn(max = 500.dp),
                    item = deal,
                    onInputEvent = onInputEvent
                )
            }

            item {
                GameDetailsFooter()
            }
        }
    }
}
