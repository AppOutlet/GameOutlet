package appoutlet.gameoutlet.feature.about

import appoutlet.gameoutlet.core.util.DesktopHelper
import appoutlet.gameoutlet.feature.common.ViewModel

class AboutViewModel(
    private val desktopHelper: DesktopHelper,
    private val aboutViewDataMapper: AboutViewDataMapper
) : ViewModel<AboutUiState, AboutInputEvent>(initialState = AboutUiState.Idle) {
    override fun onInputEvent(inputEvent: AboutInputEvent) {
        when (inputEvent) {
            is AboutInputEvent.OpenLink -> {
                desktopHelper.openLink(inputEvent.url)
            }

            AboutInputEvent.Load -> {
                mutableUiState.value = aboutViewDataMapper()
            }
        }
    }
}
