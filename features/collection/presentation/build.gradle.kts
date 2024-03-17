plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.collection.presentation"
}


dependencies {
    implementation(projects.features.collection.domain)
    implementation(projects.cores.navigation)
    implementation(projects.cores.util)
}
