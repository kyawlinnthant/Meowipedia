plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.junit5)
}
android {
    namespace = "com.everest.testrule"
}

dependencies {
    implementation(libs.coroutines.test)
    implementation(libs.jupiter.api)
    implementation(libs.junit)
}
