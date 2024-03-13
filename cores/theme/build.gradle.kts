plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
}
android {
    namespace = "com.everest.theme"
}
dependencies {
    implementation(projects.cores.model)
}
