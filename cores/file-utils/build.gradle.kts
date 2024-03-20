plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    id("kotlin-parcelize")
}
android {
    namespace = "com.everest.file.utils"
}

dependencies {
    implementation(projects.cores.dispatcher)
    implementation(projects.cores.util)
    implementation(libs.timber)
}
