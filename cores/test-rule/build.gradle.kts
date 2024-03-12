plugins {
    alias(libs.plugins.everest.android.library)
}
android {
    namespace = "com.everest.testrule"
}

dependencies {
    implementation(libs.coroutines.test)
    implementation(libs.junit)
}
