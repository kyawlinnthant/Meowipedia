plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.gallery.presentation"
}
dependencies {
    api(project(":features:gallery:domain"))
    implementation(project(":cores:navigation"))
}
