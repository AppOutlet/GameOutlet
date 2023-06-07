package appoutlet.gameoutlet.feature.about

import appoutlet.gameoutlet.core.testing.UnitTest
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class AboutViewDataMapperTest : UnitTest<AboutViewDataMapper>() {
    override fun buildSut() = AboutViewDataMapper()

    @Test
    fun shouldReturnData() {
        val actual = sut.invoke()

        with(actual) {
            assertThat(contributeEvent).isNotNull()
            assertThat(donationEvent).isNotNull()
            assertThat(websiteEvent).isNotNull()
            assertThat(twitterEvent).isNotNull()
            assertThat(mastodonEvent).isNotNull()
            assertThat(githubEvent).isNotNull()
        }
    }
}