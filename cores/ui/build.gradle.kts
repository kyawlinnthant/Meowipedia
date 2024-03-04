plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
}
android {
    namespace = "com.everest.ui"
}
dependencies {
    implementation(projects.cores.util)
    implementation(projects.cores.theme)
    implementation(projects.cores.model)
}
