@file:Suppress("DSL_SCOPE_VIOLATION", "UNUSED_VARIABLE")

import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.CoverageUnit
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kover)
    alias(libs.plugins.detekt)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlinx.gettext)
    alias(libs.plugins.gitHooks)
}

group = "appoutlet"
version = "1.4.3"

kotlin {
    jvm()

    sourceSets {
        val jvmMain by getting
        jvmMain.dependencies {
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

//
//      detektPlugins(libs.detekt.formatting)
//      detektPlugins(libs.detekt.compose)
        }

        val jvmTest by getting
        jvmTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinFixture)
            implementation(libs.truth)
            implementation(libs.mockk)
            implementation(compose.desktop.uiTestJUnit4)
        }
    }
}

compose.desktop {
    application {
        mainClass = "GameOutlet"
        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Exe,
                TargetFormat.Msi,
                TargetFormat.Deb,
                TargetFormat.Rpm
            )

            packageName = "GameOutlet"
            packageVersion = "1.4.3"
            description = "Find the best prices on PC games"
            vendor = "AppOutlet"
            licenseFile.set(project.file("LICENSE"))

            linux {
                iconFile.set(project.file("src/jvmMain/resources/image/icon.png"))
                packageName = "game-outlet"
                debMaintainer = "team.appoutlet@gmail.com"
                menuGroup = "games"
                appRelease = "1"
                appCategory = "games"
            }

            macOS {
                iconFile.set(project.file("src/jvmMain/resources/image/icon.icns"))
                bundleID = "appoutlet.gameoutlet"
                appCategory = "public.app-category.games"
            }

            windows {
                iconFile.set(project.file("src/jvmMain/resources/image/icon.ico"))
                dirChooser = true
                console = false
                menu = true
                shortcut = true
                upgradeUuid = "7f91dff9-a75a-406b-ba92-b958cb72b811"
            }

            modules(
                "java.instrument",
                "java.management",
                "java.sql",
                "jdk.unsupported",
                "java.net.http"
            )
        }

        buildTypes.release.proguard {
            isEnabled.set(true)
            configurationFiles.from(project.file("gameoutlet.pro"))
        }
    }
}

apply(from = "$rootDir/script/detekt.gradle")
apply(from = "$rootDir/script/git-hooks.gradle")
apply(from = "$rootDir/script/gettext.gradle")

// region kover setup
kover {
    reports {
        filters {
            excludes {
                classes(
                    //Dependency injection
                    "*ModuleKt*",
                    "*ModulesKt*",

                    // Non testable application setup
                    "*MainKt*",
                    "*KoinKt*",
                    "*LookAndFeelKt*",
                    "*LoggerKt*",
                    "*GameOutlet*",
                    "*OS*",

                    // Database related generated files
                    "*GameOutletDatabase*",
                    "*Queries*",

                    "*ComposableSingletons*",
                )
            }
        }

        verify {
            rule {
                bound {
                    minValue = 89
                    coverageUnits = CoverageUnit.LINE
                    aggregationForGroup = AggregationType.COVERED_PERCENTAGE
                }

                bound {
                    minValue = 77
                    coverageUnits = CoverageUnit.INSTRUCTION
                    aggregationForGroup = AggregationType.COVERED_PERCENTAGE
                }

                bound {
                    minValue = 42
                    coverageUnits = CoverageUnit.BRANCH
                    aggregationForGroup = AggregationType.COVERED_PERCENTAGE
                }
            }
        }
    }
}
// endregion


// region SQLDelight
sqldelight {
    databases {
        create("GameOutletDatabase") {
            packageName.set("appoutlet.gameoutlet.core.database")
            srcDirs("src/jvmMain/sqldelight")
            schemaOutputDirectory.set(File("src/jvmMain/sqldelight/schema"))
            verifyMigrations.set(true)
        }
    }
}
// endregion
