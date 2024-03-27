plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = libs.versions.cores.navigation.get()
}
dependencies {
    implementation(libs.hilt.navigation)
}
