plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
}
android {
    namespace = libs.versions.cores.ui.get()
}
dependencies {
    implementation(projects.cores.util)
    implementation(projects.cores.theme)
    implementation(projects.cores.model)
}
