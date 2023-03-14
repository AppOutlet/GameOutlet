@file:Suppress("DSL_SCOPE_VIOLATION", "UNUSED_VARIABLE")

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
}

compose.desktop {
    application {
        mainClass = "appoutlet.gameoutlet.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "GameOutlet"
            packageVersion = "1.0.0"
        }
    }
}

dependencies {
    commonMainImplementation(compose.desktop.currentOs)
    commonMainImplementation(libs.kotlinx.gettext)
    commonMainImplementation(libs.koin)
    commonMainImplementation(libs.voyager.navigator)
    commonMainImplementation(libs.voyager.koin)
    commonMainImplementation(libs.voyager.transitions)
    commonMainImplementation(libs.sqldelight.sqliteDriver)
    commonMainImplementation(libs.retrofit)
    commonMainImplementation(libs.retrofit.moshi)

    commonTestImplementation(libs.kotlin.test)
    commonTestImplementation(libs.truth)

    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.compose)
}

apply(from = "$rootDir/script/detekt.gradle")
apply(from = "$rootDir/script/git-hooks.gradle")
apply(from = "$rootDir/script/kover.gradle")
apply(from = "$rootDir/script/test.gradle")
apply(from = "$rootDir/script/gettext.gradle")
apply(from = "$rootDir/script/sqldelight.gradle")
