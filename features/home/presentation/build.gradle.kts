plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.home.presentation"
}
dependencies {
    implementation(project(":features:home:domain"))
    implementation(project(":cores:navigation"))
    implementation(project(":cores:ui"))
    implementation(project(":cores:util"))
    implementation(project(":cores:theme"))
}
