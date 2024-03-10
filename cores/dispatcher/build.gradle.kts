plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = libs.versions.cores.dispatchers.get()
}
