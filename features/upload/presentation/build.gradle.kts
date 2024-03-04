plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.upload.presentation"
}
dependencies {
    implementation(projects.features.upload.domain)
    implementation(projects.cores.navigation)
    implementation(projects.cores.fileUtils)
    implementation(projects.cores.util)
}
