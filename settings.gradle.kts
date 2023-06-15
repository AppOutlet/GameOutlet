@file:Suppress("UnstableApiUsage")

fun shouldEnableFlatDir(): Boolean {
    val context = providers.environmentVariable("CONTEXT").orNull
    return context == "FLATPAK"
}

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        if(shouldEnableFlatDir()) {
            flatDir {
                dir(file("dependencies"))
            }
        }
    }
}

rootProject.name = "GameOutlet"

