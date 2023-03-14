package appoutlet.gameoutlet.feature.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun FirstLoad(block: () -> Unit) {
    var shouldLoad by remember { mutableStateOf(true) }
    if (shouldLoad) {
        shouldLoad = false
        block()
    }
}
