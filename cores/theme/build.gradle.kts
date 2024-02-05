@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.compose.library)
}
android {
    namespace = "com.everest.theme"
}