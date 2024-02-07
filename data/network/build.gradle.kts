plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.network)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.network"
}
dependencies {
    implementation(project(":cores:util"))
}
