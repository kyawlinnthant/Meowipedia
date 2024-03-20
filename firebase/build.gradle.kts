plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.firebase)
    alias(libs.plugins.android.application).apply(false)
}

android {
    namespace = "com.everest.firebase"

    tasks.withType<Test> {
        useJUnitPlatform() // Make all tests use JUnit 5
    }
}
