package appoutlet.gameoutlet.feature.gamesearch.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameSearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {
    var internalValue by remember { mutableStateOf(value) }
    Box(modifier = modifier) {
        TextField(
            modifier = Modifier.semantics { testTag = "searchView" }.fillMaxWidth(),
            value = internalValue,
            leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            onValueChange = {
                internalValue = it
                onValueChange(internalValue)
            },
            shape = MaterialTheme.shapes.extraLarge,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(text = i18n.tr("Type your favorite game title"))
            },
            singleLine = true,
        )

        AnimatedVisibility(modifier = Modifier.align(Alignment.CenterEnd), visible = isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .semantics { testTag = "loadingIndicator" }
                    .padding(end = MaterialTheme.spacing.medium)
                    .progressSemantics()
                    .size(36.dp)
            )
        }
    }
}

@Composable
@Preview
private fun GameSearchTextFieldPreview() {
    GameOutletTheme {
        GameSearchTextField(onValueChange = {}, isLoading = true, value = "")
    }
}
