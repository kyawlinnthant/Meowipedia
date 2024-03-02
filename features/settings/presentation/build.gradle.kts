plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.settings.presentation"
}
dependencies {
    implementation(project(":features:settings:domain"))
    implementation(project(":cores:util"))
    implementation(project(":cores:model"))
    implementation(project(":cores:ui"))
    implementation(project(":cores:theme"))
    implementation(project(":cores:navigation"))
}
