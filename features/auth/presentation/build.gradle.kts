plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.firebase)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.auth.presentation"
}
dependencies {
    implementation(projects.features.auth.domain)

    implementation(projects.firebase)
    implementation(projects.cores.navigation)
    implementation(projects.cores.ui)
    implementation(projects.cores.util)
    implementation(projects.cores.theme)
}
