plugins {
    alias(libs.plugins.everest.android.application)
    alias(libs.plugins.everest.compose.application)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.firebase)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.everest.meowipedia"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.everest.meowipedia"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z ( Major.Minor.Patch)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true

        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }

        resourceConfigurations += arrayOf(
            "en",
            "fr"
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
dependencies {
    implementation(projects.features.auth.presentation)
    implementation(projects.features.home.presentation)
    implementation(projects.features.settings.presentation)
    implementation(projects.features.collection.presentation)

    implementation(projects.features.settings.domain)

    implementation(projects.cores.navigation)
    implementation(projects.cores.fileUtils)
    implementation(projects.cores.model)
    implementation(projects.cores.theme)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.appcompat.resource)

    implementation(libs.timber)
}
