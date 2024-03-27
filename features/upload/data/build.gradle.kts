plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = libs.versions.features.upload.data.get()
}

dependencies {
    api(projects.data.network)
    implementation(projects.cores.util)
    implementation(projects.cores.dispatcher)
}
