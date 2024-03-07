plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.network)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)

    alias(libs.plugins.junit5)
}

android {
    namespace = "com.everest.network"

    buildFeatures {
        buildConfig = true
    }
}
dependencies {
    implementation(projects.cores.util)
}
