plugins {
    alias(libs.plugins.everest.android.library)
}
android {
    namespace = "com.everest.util"
}

dependencies {
    implementation(libs.timber)
}
