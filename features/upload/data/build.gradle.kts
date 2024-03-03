plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.upload.data"
}

dependencies {
    api(projects.data.network)
    implementation(projects.cores.util)
    implementation(projects.cores.dispatcher)
}
