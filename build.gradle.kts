@file:Suppress("DSL_SCOPE_VIOLATION", "UNUSED_VARIABLE")
@file:OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.commitlint)
    alias(libs.plugins.gitHooks)
    alias(libs.plugins.kover)
    alias(libs.plugins.kotlinx.gettext)
    alias(libs.plugins.sqldelight)
}

group = "appoutlet"
version = "1.3.6"

compose.desktop {
    application {
        mainClass = "GameOutlet"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Rpm)

            packageName = "GameOutlet"
            packageVersion = "1.3.6"
            description = "Find the best prices on PC games"
            vendor = "AppOutlet"
            licenseFile.set(project.file("LICENSE"))

            linux {
                iconFile.set(project.file("src/main/resources/image/icon.png"))
                packageName = "game-outlet"
                debMaintainer = "team.appoutlet@gmail.com"
                menuGroup = "games"
                appRelease = "1"
                appCategory = "games"
            }

            macOS {
                iconFile.set(project.file("src/main/resources/image/icon.icns"))
                bundleID = "appoutlet.gameoutlet"
                appCategory = "public.app-category.games"
            }

            windows {
                iconFile.set(project.file("src/main/resources/image/icon.ico"))
                dirChooser = true
                console = true
                menu = true
                shortcut = true
                upgradeUuid = "7f91dff9-a75a-406b-ba92-b958cb72b811"
            }

            modules("java.instrument", "java.management", "java.sql", "jdk.unsupported", "java.net.http")
        }

        buildTypes.release.proguard {
            isEnabled.set(true)
            configurationFiles.from(project.file("gameoutlet.pro"))
        }
    }
}

dependencies {
    implementation(compose.desktop.currentOs) {
        exclude("org.jetbrains.compose.material")
    }
    implementation(compose.material3)
    implementation(compose.materialIconsExtended)
    implementation(libs.kotlinx.gettext)
    implementation(libs.koin)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.tabNavigation)
    implementation(libs.sqldelight.sqliteDriver)
    implementation(libs.sqldelight.coroutinesExtensions)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.kamel)
    implementation(libs.ktor.client)
    implementation(libs.moshi.kotlin)
    implementation(libs.slf4j.simple)
    implementation(libs.moneta)
    implementation(libs.joda.time)
    implementation(libs.flatlaf)
    implementation(libs.napier)
    implementation(libs.appdirs)
    implementation(libs.themeDetector)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinFixture)
    testImplementation(libs.truth)
    testImplementation(libs.mockk)
    testImplementation(compose.desktop.uiTestJUnit4)

    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.compose)
}

apply(from = "$rootDir/script/detekt.gradle")
apply(from = "$rootDir/script/git-hooks.gradle")
apply(from = "$rootDir/script/kover.gradle")
apply(from = "$rootDir/script/gettext.gradle")
apply(from = "$rootDir/script/sqldelight.gradle")
