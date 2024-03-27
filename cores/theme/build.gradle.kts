plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
}
android {
    namespace = libs.versions.cores.theme.get()
}
dependencies {
    implementation(projects.cores.model)
}
