plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.categories.presentation"
}
dependencies {
    implementation(project(":features:categories:domain"))
    implementation(project(":cores:util"))
    implementation(project(":cores:ui"))
    api(project(":cores:theme"))
}
