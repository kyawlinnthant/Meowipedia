plugins {
    alias(libs.plugins.everest.android.library)
}
android {
    namespace = libs.versions.cores.test.rule.get()
}

dependencies {
    implementation(libs.coroutines.test)
    implementation(libs.junit)
}
