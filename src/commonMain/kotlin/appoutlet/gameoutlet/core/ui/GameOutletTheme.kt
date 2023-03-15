package appoutlet.gameoutlet.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun GameOutletTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}

object GameOutletTheme {
    val spacing = Spacing
}
