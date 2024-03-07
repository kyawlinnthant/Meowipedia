plugins {
    alias(libs.plugins.everest.android.application)
    alias(libs.plugins.everest.compose.application)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.google.services)

    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
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
dependencies {
    implementation(projects.features.auth.presentation)
    implementation(projects.features.home.presentation)
    implementation(projects.features.settings.presentation)
    implementation(projects.features.upload.presentation)
    implementation(projects.features.settings.domain)
    implementation(projects.cores.navigation)
    implementation(projects.cores.fileUtils)
    implementation(projects.cores.model)
    implementation(projects.cores.theme)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}


secrets {
    // Change the properties file from the default "local.properties" in your root project
    // to another properties file in your root project.
    propertiesFileName = "credetntials.properties"

    // A properties file containing default secret values. This file can be checked in version
    // control.
    defaultPropertiesFileName = "secrets.defaults.properties"

}
