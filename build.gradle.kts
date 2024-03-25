@file:Suppress("DSL_SCOPE_VIOLATION", "UNUSED_VARIABLE")
@file:OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.commitlint)
    alias(libs.plugins.gitHooks)
    alias(libs.plugins.kover)
    alias(libs.plugins.kotlinx.gettext)
    alias(libs.plugins.sqldelight)
}

group = "appoutlet"
version = "1.3.4"

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }

    sourceSets {
        val jvmMain by getting
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "GameOutlet"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Rpm)

            packageName = "GameOutlet"
            packageVersion = "1.3.4"
            description = "Find the best prices on PC games"
            vendor = "AppOutlet"
            licenseFile.set(project.file("LICENSE"))

            linux {
                iconFile.set(project.file("src/commonMain/resources/image/icon.png"))
                packageName = "game-outlet"
                debMaintainer = "team.appoutlet@gmail.com"
                menuGroup = "games"
                appRelease = "1"
                appCategory = "games"
            }

            macOS {
                iconFile.set(project.file("src/commonMain/resources/image/icon.icns"))
                bundleID = "appoutlet.gameoutlet"
                appCategory = "public.app-category.games"
            }

            windows {
                iconFile.set(project.file("src/commonMain/resources/image/icon.ico"))
                dirChooser = true
                console = false
                menu = true
                shortcut = true
                upgradeUuid = "7f91dff9-a75a-406b-ba92-b958cb72b81e"
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
    commonMainImplementation(compose.desktop.currentOs) {
        exclude("org.jetbrains.compose.material")
    }
    commonMainImplementation(compose.material3)
    commonMainImplementation(compose.materialIconsExtended)
    commonMainImplementation(libs.kotlinx.gettext)
    commonMainImplementation(libs.koin)
    commonMainImplementation(libs.voyager.navigator)
    commonMainImplementation(libs.voyager.transitions)
    commonMainImplementation(libs.voyager.tabNavigation)
    commonMainImplementation(libs.sqldelight.sqliteDriver)
    commonMainImplementation(libs.sqldelight.coroutinesExtensions)
    commonMainImplementation(libs.retrofit)
    commonMainImplementation(libs.retrofit.moshi)
    commonMainImplementation(libs.kamel)
    commonMainImplementation(libs.ktor.client)
    commonMainImplementation(libs.moshi.kotlin)
    commonMainImplementation(libs.slf4j.simple)
    commonMainImplementation(libs.moneta)
    commonMainImplementation(libs.joda.time)
    commonMainImplementation(libs.flatlaf)
    commonMainImplementation(libs.napier)
    commonMainImplementation(libs.appdirs)
    commonMainImplementation(libs.themeDetector)

    commonTestImplementation(libs.kotlin.test)
    commonTestImplementation(libs.kotlinFixture)
    commonTestImplementation(libs.truth)
    commonTestImplementation(libs.mockk)
    commonTestImplementation(compose.desktop.uiTestJUnit4)

    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.compose)
}

apply(from = "$rootDir/script/detekt.gradle")
apply(from = "$rootDir/script/git-hooks.gradle")
apply(from = "$rootDir/script/kover.gradle")
apply(from = "$rootDir/script/gettext.gradle")
apply(from = "$rootDir/script/sqldelight.gradle")
