package appoutlet.gameoutlet.feature.splash

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import name.kropp.kotlinx.gettext.Locale


@Composable
fun SplashScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.weight(1f)) { }
        Text(modifier = Modifier.padding(vertical = 4.dp), text = Locale.getDefault().toLanguageTag())
        Text(modifier = Modifier.padding(vertical = 4.dp), text = "Powered by App Outlet")
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreen()
}
