plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = libs.versions.features.auth.presentation.get()
}
dependencies {
    implementation(projects.features.auth.domain)
    implementation(projects.cores.navigation)
    implementation(projects.cores.ui)
    implementation(projects.cores.util)
    implementation(projects.cores.theme)
}
