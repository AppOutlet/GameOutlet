package appoutlet.gameoutlet.feature.about

import appoutlet.gameoutlet.feature.about.AboutInputEvent.OpenLink
import appoutlet.gameoutlet.feature.about.AboutUiState.Loaded

class AboutViewDataMapper {
    operator fun invoke() = Loaded(
        contributeEvent = OpenLink(
            "https://github.com/AppOutlet/GameOutlet/blob/main/CONTRIBUTING.md#-contributors-guide"
        ),
        donationEvent = OpenLink("https://ko-fi.com/appoutlet"),
        websiteEvent = OpenLink("https://appoutlet.github.io/"),
        twitterEvent = OpenLink("https://twitter.com/AppOutletTeam"),
        mastodonEvent = OpenLink("https://mastodon.social/@AppOutlet"),
        githubEvent = OpenLink("https://github.com/AppOutlet/GameOutlet"),
    )
}
