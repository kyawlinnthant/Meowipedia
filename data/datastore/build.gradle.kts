@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.datastore)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.datastore"
}