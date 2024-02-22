plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.favourite.presentation"
}
dependencies {
    implementation(project(":features:favourite:domain"))
}
