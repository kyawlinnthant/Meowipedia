plugins {
    alias(libs.plugins.everest.android.application)
    alias(libs.plugins.everest.compose.application)
}

android {
    namespace = "com.everest.meowipedia"

    defaultConfig {
        applicationId = "com.everest.meowipedia"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z ( Major.Minor.Patch)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
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