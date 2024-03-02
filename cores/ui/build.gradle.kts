plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
}
android {
    namespace = "com.everest.ui"
}
dependencies {
    implementation(project(":cores:util"))
    implementation(project(":cores:theme"))
    implementation(project(":cores:model"))
}
