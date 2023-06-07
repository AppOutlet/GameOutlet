package appoutlet.gameoutlet.feature.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Pages
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.common.View
import org.koin.core.component.get

private const val version = "1.2.0"

class AboutView : View<AboutUiState, AboutInputEvent>() {
    override val viewModel = get<AboutViewModel>()

    @Composable
    override fun ViewContent(uiState: AboutUiState, onInputEvent: (AboutInputEvent) -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(120.dp), painter = painterResource("image/icon.png"), contentDescription = null
            )

            Text(text = "GameOutlet", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = i18n.tr("Version {{version}}", "version" to version), style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            Card(modifier = Modifier.width(360.dp)) {
                Column(modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacing.medium)) {
                    Text(
                        text = i18n.tr("Authors"),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Messias Junior", style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                    ) {
                        OutlinedButton(modifier = Modifier.weight(1f), onClick = {}) {
                            Icon(imageVector = Icons.Outlined.Help, contentDescription = null)
                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                            Text(text = i18n.tr("Contribute"))
                        }

                        OutlinedButton(modifier = Modifier.weight(1f), onClick = {}) {
                            Icon(imageVector = Icons.Outlined.Money, contentDescription = null)
                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                            Text(text = i18n.tr("Donate"))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Outlined.Language,
                        contentDescription = null
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource("image/twitter.svg"),
                        contentDescription = null
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource("image/mastodon.svg"),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = i18n.tr("Powered by App Outlet"),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
