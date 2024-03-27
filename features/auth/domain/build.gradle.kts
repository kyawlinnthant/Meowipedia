plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.firebase)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = libs.versions.features.auth.domain.get()
}

dependencies {
    implementation(projects.features.auth.data)
    implementation(projects.cores.util)
}
