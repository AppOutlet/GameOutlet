package appoutlet.gameoutlet.feature.store.composable

import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.store.StoreInputEvent
import io.github.aakira.napier.Napier
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreTopAppBar(
    store: Store,
    onInputEvent: (StoreInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    GameOutletTheme(getIfItsDarkTheme(store)) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            navigationIcon = {
                IconButton(
                    modifier = Modifier.testTag("navigateBack"),
                    onClick = { onInputEvent(StoreInputEvent.NavigateBack) },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = i18n.tr("Navigate back")
                    )
                }
            },
            title = {
                store.bannerUrl?.let {
                    KamelImage(
                        modifier = Modifier,
                        resource = lazyPainterResource(data = store.bannerUrl),
                        contentDescription = null,
                        animationSpec = tween(),
                        contentScale = ContentScale.Inside
                    )
                }
            },
        )
    }
}

@Suppress("MagicNumber")
private fun getIfItsDarkTheme(store: Store): Boolean {
    Napier.i("${store.id}, // ${store.name}")
    return when (store.id) {
        1, // Steam
        2, // GamersGame
        3, // GreenManGaming
        7, // GOG
        13, // Uplay
        15, // Fanatical
        21, // WinGameStore
        27, // Gamesplanet
        30, // IndieGala
        33, // DLGamer
        34, // Noctre
        35, // DreamGame
        -> true

        else -> false
    }
}
