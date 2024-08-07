package appoutlet.gameoutlet.feature.common.composable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.GameOutletTheme
import appoutlet.gameoutlet.core.ui.spacing

@Composable
fun Error(
    modifier: Modifier = Modifier,
    title: String = i18n.tr("Something went wrong"),
    message: String = i18n.tr("An unexpected error occurred"),
    buttonText: String = i18n.tr("Try again"),
    onTryAgain: (() -> Unit)? = null
) {
    Column(
        modifier = modifier.semantics { testTag = "errorLayout" },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.semantics { testTag = "title" },
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            modifier = Modifier.semantics { testTag = "message" },
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        onTryAgain?.let {
            Button(modifier = Modifier.semantics { testTag = "button" }, onClick = onTryAgain) {
                Text(buttonText)
            }
        }
    }
}

@Composable
@Preview
private fun ErrorPreview() {
    GameOutletTheme {
        Error {
        }
    }
}
