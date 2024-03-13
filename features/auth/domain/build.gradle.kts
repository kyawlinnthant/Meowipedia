plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.firebase)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.auth.domain"
}

dependencies {
    implementation(projects.features.auth.data)
    implementation(projects.cores.util)
}
