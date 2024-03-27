plugins {
    alias(libs.plugins.everest.android.library)
}
android {
    namespace = libs.versions.cores.model.get()
}
