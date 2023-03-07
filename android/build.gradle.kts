plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

repositories {
    mavenCentral()
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "appoutlet.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.androidx.activityCompose)
}
