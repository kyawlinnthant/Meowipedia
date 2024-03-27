plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    id("kotlin-parcelize")
}
android {
    namespace = libs.versions.cores.file.utils.get()
}

dependencies {
    implementation(projects.cores.dispatcher)
}
