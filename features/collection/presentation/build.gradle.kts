plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.firebase)
}

android {
    namespace = "com.everest.collection.presentation"
}

dependencies {
    implementation(projects.features.collection.domain)

    implementation(projects.cores.navigation)
    implementation(projects.cores.theme)

    implementation(projects.cores.util)
    implementation(projects.cores.ui)
    implementation(projects.cores.fileUtils)

    implementation(projects.firebase)
}
