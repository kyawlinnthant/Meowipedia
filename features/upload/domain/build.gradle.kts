plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.upload.domain"

    tasks.withType<Test> {
        useJUnitPlatform() // Make all tests use JUnit 5
    }
}

dependencies {
    implementation(projects.features.upload.data)
    implementation(projects.cores.util)
}
