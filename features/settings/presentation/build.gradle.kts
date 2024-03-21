plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.firebase)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.settings.presentation"
}
dependencies {
    implementation(projects.features.settings.domain)
    implementation(projects.firebase)

    implementation(projects.cores.util)
    implementation(projects.cores.model)
    implementation(projects.cores.ui)
    implementation(projects.cores.theme)
    implementation(projects.cores.navigation)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.appcompat.resource)
}
