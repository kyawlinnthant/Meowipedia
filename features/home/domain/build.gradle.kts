plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.home.domain"
}

dependencies {
    implementation(projects.features.home.data)
    implementation(projects.cores.util)
}
