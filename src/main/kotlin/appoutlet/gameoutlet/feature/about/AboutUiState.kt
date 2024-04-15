package appoutlet.gameoutlet.feature.about

import appoutlet.gameoutlet.feature.common.UiState

sealed interface AboutUiState : UiState {
    object Idle : AboutUiState

    data class Loaded(
        val contributeEvent: AboutInputEvent?,
        val donationEvent: AboutInputEvent?,
        val websiteEvent: AboutInputEvent?,
        val twitterEvent: AboutInputEvent?,
        val mastodonEvent: AboutInputEvent?,
        val githubEvent: AboutInputEvent?,
    ) : AboutUiState
}
