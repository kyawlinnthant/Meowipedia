plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
    id("kotlin-parcelize")
}
android {
    namespace = "com.everest.file.utils"
}

dependencies {
    implementation(project(":cores:dispatcher"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}
