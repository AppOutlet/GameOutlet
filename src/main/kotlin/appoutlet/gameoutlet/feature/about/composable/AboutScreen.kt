package appoutlet.gameoutlet.feature.about.composable

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
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import appoutlet.gameoutlet.core.translation.i18n
import appoutlet.gameoutlet.core.ui.spacing
import appoutlet.gameoutlet.feature.about.AboutInputEvent
import appoutlet.gameoutlet.feature.about.AboutUiState

const val VERSION = "1.4.1"

@Composable
fun AboutScreen(
    uiState: AboutUiState,
    onInputEvent: (AboutInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        AboutUiState.Idle -> onInputEvent(AboutInputEvent.Load)
        is AboutUiState.Loaded -> AboutContent(
            uiState = uiState,
            onInputEvent = onInputEvent,
            modifier = modifier,
        )
    }
}

@Composable
private fun AboutContent(
    uiState: AboutUiState.Loaded,
    onInputEvent: (AboutInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AboutHeader()

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        Card(modifier = Modifier.width(256.dp)) {
            Column(modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacing.medium)) {
                AboutAuthors()

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                uiState.contributeEvent?.let { event ->
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onInputEvent(event)
                        },
                    ) {
                        Icon(imageVector = Icons.Outlined.Handshake, contentDescription = null)
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                        Text(text = i18n.tr("Contribute to project"))
                    }
                }

                uiState.donationEvent?.let { event ->
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onInputEvent(event)
                        },
                    ) {
                        Icon(imageVector = Icons.Outlined.Coffee, contentDescription = null)
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                        Text(text = i18n.tr("Buy me a coffee"))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        AboutSocialLinks(uiState = uiState, onInputEvent = onInputEvent)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            text = i18n.tr("Powered by AppOutlet"),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun AboutHeader(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(120.dp).testTag("appIcon"),
            painter = painterResource("image/icon.png"),
            contentDescription = null
        )

        Text(text = "GameOutlet", style = MaterialTheme.typography.headlineMedium)

        Text(
            text = i18n.tr("Version {{version}}", "version" to VERSION),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun AboutAuthors(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = i18n.tr("Authors"),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Messias Junior",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun AboutSocialLinks(
    uiState: AboutUiState.Loaded,
    onInputEvent: (AboutInputEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        uiState.websiteEvent?.let { event ->
            IconButton(
                modifier = Modifier.testTag("websiteIcon"),
                onClick = {
                    onInputEvent(event)
                },
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null
                )
            }
        }

        uiState.twitterEvent?.let { event ->
            IconButton(
                modifier = Modifier.testTag("twitterIcon"),
                onClick = { onInputEvent(event) },
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource("image/twitter.svg"),
                    contentDescription = null
                )
            }
        }

        uiState.mastodonEvent?.let { event ->
            IconButton(
                modifier = Modifier.testTag("mastodonIcon"),
                onClick = { onInputEvent(event) },
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource("image/mastodon.svg"),
                    contentDescription = null
                )
            }
        }

        uiState.githubEvent?.let { event ->
            IconButton(
                modifier = Modifier.testTag("githubIcon"),
                onClick = { onInputEvent(event) },
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource("image/github.svg"),
                    contentDescription = null
                )
            }
        }
    }
}
