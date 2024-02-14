plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.settings.presentation"
}
dependencies {
    api(project(":features:settings:domain"))
    implementation(project(":cores:util"))
    implementation(project(":cores:ui"))
    api(project(":cores:theme"))
    api(project(":cores:navigation"))
}
