plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = libs.versions.features.upload.domain.get()
}

dependencies {
    implementation(projects.features.upload.data)
    implementation(projects.cores.util)
}
