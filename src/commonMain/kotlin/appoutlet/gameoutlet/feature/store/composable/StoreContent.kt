package appoutlet.gameoutlet.feature.store.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.feature.store.StoreInputEvent
import appoutlet.gameoutlet.feature.store.StoreUiState

@Suppress("UnusedParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreContent(
    uiState: StoreUiState,
    onInputEvent: (StoreInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onInputEvent(StoreInputEvent.NavigateBack) }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = i18n.tr("Navigate back")
                )
            }
        },
        title = {},
    )
}
