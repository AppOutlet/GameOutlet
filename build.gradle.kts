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
version = "1.0-SNAPSHOT"

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }

    sourceSets {
        val jvmMain by getting
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "appoutlet.gameoutlet.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "GameOutlet"
            packageVersion = "1.0.0"
            modules("java.sql")
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
    commonMainImplementation(libs.retrofit)
    commonMainImplementation(libs.retrofit.moshi)
    commonMainImplementation(libs.kamel)
    commonMainImplementation(libs.ktor.client)
    commonMainImplementation(libs.slf4j.simple)
    commonMainImplementation(libs.moneta)
    commonMainImplementation(libs.joda.time)

    commonTestImplementation(libs.kotlin.test)
    commonTestImplementation(libs.kotlinFixture)
    commonTestImplementation(libs.truth)
    commonTestImplementation(libs.mockk)
    commonTestImplementation(compose.uiTestJUnit4)

    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.compose)
}

apply(from = "$rootDir/script/detekt.gradle")
apply(from = "$rootDir/script/git-hooks.gradle")
apply(from = "$rootDir/script/kover.gradle")
apply(from = "$rootDir/script/test.gradle")
apply(from = "$rootDir/script/gettext.gradle")
apply(from = "$rootDir/script/sqldelight.gradle")
