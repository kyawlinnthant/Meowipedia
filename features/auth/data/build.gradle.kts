plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.firebase)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = libs.versions.features.auth.data.get()
}
dependencies {
    implementation(projects.cores.util)
    implementation(projects.cores.dispatcher)
}
